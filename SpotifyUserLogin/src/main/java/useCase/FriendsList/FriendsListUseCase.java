package useCase.FriendsList;

import api.SpotifyInteractor;
import entities.users.FriendProfile;
import entities.users.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Use case interactor for the friends list.
 */
public class FriendsListUseCase {
    private final UserProfile userProfile;
    private final List<FriendProfile> friendProfileList = new ArrayList<>();

    /**
     * Intended use constructor, which connects to the Spotify API.
     * @param interactor the Spotify Interactor for this session.
     * @param userProfile the current user.
     */
    public FriendsListUseCase(SpotifyInteractor interactor, UserProfile userProfile) {
        this.userProfile = userProfile;

        for (String friendId : userProfile.getFriendsListIds()) {
            friendProfileList.add(new FriendProfile(interactor, friendId));
        }
    }

    /**
     * Simpler constructor for easier testing.
     * @param userProfile a sample user.
     * @param friendProfileList a sample friends list.
     */
    public FriendsListUseCase(UserProfile userProfile, List<FriendProfile> friendProfileList) {
        this.userProfile = userProfile;
        this.friendProfileList.addAll(friendProfileList);
    }

    public List<String> getFriendsListNames() {
        return userProfile.getFriendsListNames();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public List<FriendProfile> getFriendProfileList() {
        return friendProfileList;
    }
}
