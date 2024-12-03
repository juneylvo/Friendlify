package TestUsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.users.FriendProfile;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFriendsListUseCase {
    public static String userHome = System.getProperty("user.home");
    public static String workingDirectory = "%s/IdeaProjects/Group-72/SpotifyUserLogin".formatted(userHome);
    public static String resourcesDirectory = "%s/src/test/resources/sampleUsers.json".formatted(workingDirectory);

    @BeforeClass
    public static void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(resourcesDirectory));
        JsonNode userData = jsonNode.get("testUser1");
        JsonNode friendData1 = jsonNode.get("testUser2");
        JsonNode friendData2 = jsonNode.get("testUser3");

        FriendProfile friendProfile1 = new FriendProfile(
                friendData1.get("username").toString(),
                friendData1.get("userId").toString(),
                objectMapper.readValue(friendData1.get("genres").traverse(), new TypeReference<>() {}),
                objectMapper.readValue(friendData1.get("artists").traverse(), new TypeReference<>() {})
        );
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