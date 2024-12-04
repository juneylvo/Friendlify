package TestUsers;

import api.SpotifyInteractor;
import entities.users.UserProfile;
import useCase.FriendsList.FriendsListUseCase;

import static org.junit.Assert.assertEquals;

public class TestFriendsListUseCaseConstructor {
    public static void testConstructor() {
        SpotifyInteractor interactor = new SpotifyInteractor();
        interactor.login();
        UserProfile user = new UserProfile(interactor);
        FriendsListUseCase useCase = new FriendsListUseCase(interactor, user);
        assertEquals(useCase.getUserProfile(), user);
    }

    public static void main(String[] args) {
        testConstructor();
    }
}
