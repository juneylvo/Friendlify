package TestUsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.users.FriendProfile;
import entities.users.UserProfile;
import org.json.JSONArray;
import org.junit.BeforeClass;
import org.junit.Test;
import useCase.FriendsList.FriendsListUseCase;

import java.io.File;
import java.util.ArrayList;
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

        JSONArray friends = new JSONArray();
        friends.put(friendProfile1);
        friends.put(friendProfile2);
        userProfile = helper.createUserProfile("testUser1", friends);
    }

    @Test
    public void getFriendsListNames() {
    }

    @Test
    public void getUserProfile() {
    }

    @Test
    public void getFriendProfileList() {
    }
}