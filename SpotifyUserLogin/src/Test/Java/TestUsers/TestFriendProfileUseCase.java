package TestUsers;

import entities.users.FriendProfile;
import junit.framework.TestCase;
import useCase.FriendsList.FriendProfileUseCase;

import java.util.Arrays;
import java.util.List;

public class TestFriendProfileUseCase extends TestCase {
    private static final String username = "Username";
    private static final String userId = "UserId";
    private static final List<String> genres = Arrays.asList("Genre 1", "Genre 2", "Genre 3");
    private static final List<String> artists = Arrays.asList("Artist 1", "Artist 2", "Artist 3");

    private static final FriendProfile friendProfile = new FriendProfile(username, userId, genres, artists);

    private static final FriendProfileUseCase friendProfileUseCase = new FriendProfileUseCase(friendProfile);

    public void testGetPreferredGenres() {
        assertEquals(friendProfileUseCase.getPreferredGenres(), Arrays.asList("Genre 1", "Genre 2", "Genre 3"));
    }

    public void testGetPreferredArtists() {
        assertEquals(friendProfileUseCase.getPreferredArtists(), Arrays.asList("Artist 1", "Artist 2", "Artist 3"));
    }

    public void testGetUsername() {
        assertEquals(friendProfileUseCase.getUsername(), username);
    }

    public void testFriendProfile() {
        assertEquals(friendProfile, friendProfileUseCase.friendProfile());
    }
}