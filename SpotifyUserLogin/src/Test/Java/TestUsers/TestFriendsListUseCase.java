package TestUsers;

import entities.users.FriendProfile;
import entities.users.UserProfile;
import org.json.JSONArray;
import org.junit.BeforeClass;
import org.junit.Test;
import useCase.FriendsList.FriendsListUseCase;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFriendsListUseCase {
    private static FriendProfile friendProfile1;
    private static FriendProfile friendProfile2;
    private static UserProfile userProfile;

    private static FriendsListUseCase friendsListUseCase;

    @BeforeClass
    public static void setUp() throws Exception {
        TestUsersHelper helper = new TestUsersHelper();
        friendProfile1 = helper.createFriendProfile("testUser2");
        friendProfile2 = helper.createFriendProfile("testUser3");

        List<FriendProfile> friendProfiles = Arrays.asList(friendProfile1, friendProfile2);

        JSONArray friends = helper.createUserFriendsList(friendProfiles);
        userProfile = helper.createUserProfile("testUser1", friends);

        friendsListUseCase = new FriendsListUseCase(userProfile, friendProfiles);
    }

    @Test
    public void getFriendsListNames() {
        List<String> friendsListNames = Arrays.asList("two", "three");
        assertEquals(friendsListUseCase.getFriendsListNames(), friendsListNames);
    }

    @Test
    public void getUserProfile() {
        assertEquals(friendsListUseCase.getUserProfile(), userProfile);
    }

    @Test
    public void getFriendProfileList() {
        assertEquals(friendsListUseCase.getFriendProfileList(), Arrays.asList(friendProfile1, friendProfile2));
    }
}