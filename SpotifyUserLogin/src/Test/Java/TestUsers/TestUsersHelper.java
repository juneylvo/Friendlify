package TestUsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.users.FriendProfile;
import entities.users.UserProfile;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;

public class TestUsersHelper {
    public static String userHome = System.getProperty("user.home");
    public static String workingDirectory = "%s/IdeaProjects/Group-72/SpotifyUserLogin".formatted(userHome);
    public static String resourcesDirectory = "%s/src/test/resources/sampleUsers.json".formatted(workingDirectory);

    private static final ObjectMapper mapper = new ObjectMapper();
    private static JsonNode jsonNode;

    public TestUsersHelper() {
        JsonNode jsonNode1;
        try {
            jsonNode1 = mapper.readTree(new File(resourcesDirectory));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            jsonNode1 = null;
        }
        jsonNode = jsonNode1;
    }

    public FriendProfile createFriendProfile(String user) throws IOException {
        JsonNode userData = jsonNode.get(user);

        return new FriendProfile(
                userData.get("username").asText(),
                userData.get("userId").asText(),
                mapper.readValue(userData.get("genres").traverse(), new TypeReference<>() {}),
                mapper.readValue(userData.get("artists").traverse(), new TypeReference<>() {})
        );
    }

    public UserProfile createUserProfile(String user, JSONArray friendsList) throws IOException {
        JsonNode userData = jsonNode.get(user);

        return new UserProfile(
                userData.get("username").asText(),
                userData.get("userId").asText(),
                mapper.readValue(userData.get("genres").traverse(), new TypeReference<>() {}),
                mapper.readValue(userData.get("artists").traverse(), new TypeReference<>() {}),
                friendsList
        );
    }
}
