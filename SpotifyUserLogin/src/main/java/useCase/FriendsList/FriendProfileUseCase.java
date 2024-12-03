package useCase.FriendsList;

import entities.users.FriendProfile;

import java.util.List;

/**
 * Use case interactor for the friend profile.
 */
public record FriendProfileUseCase(FriendProfile friendProfile) {

    public List<String> getPreferredGenres() {
        return friendProfile.getPreferredGenres();
    }

    public List<String> getPreferredArtists() {
        return friendProfile.getPreferredArtists();
    }

    public String getUsername() {
        return friendProfile.getUsername();
    }
}
