package TestUsers;

import entities.users.FriendProfile;
import org.junit.Before;
import org.junit.Test;
import useCase.FriendsList.FriendProfileUseCase;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestFriendProfileUseCase {
    private static FriendProfile friendProfile;
    private static FriendProfileUseCase friendProfileUseCase;

    @Before
    public void setUp() throws Exception {
        TestUsersHelper helper = new TestUsersHelper();
        friendProfile = helper.createFriendProfile("testUser1");
        friendProfileUseCase = new FriendProfileUseCase(friendProfile);
    }

    @Test
    public void getPreferredGenres() {
        assertEquals(friendProfileUseCase.getPreferredGenres(), Arrays.asList("Genre 11", "Genre 12", "Genre 13"));
    }

    @Test
    public void getPreferredArtists() {
        assertEquals(friendProfileUseCase.getPreferredArtists(), Arrays.asList("Artist 11", "Artist 12", "Artist 13"));
    }

    @Test
    public void getUsername() {
        assertEquals(friendProfileUseCase.getUsername(), "one");
    }

    @Test
    public void friendProfile() {
        assertEquals(friendProfile, friendProfileUseCase.friendProfile());
    }
}