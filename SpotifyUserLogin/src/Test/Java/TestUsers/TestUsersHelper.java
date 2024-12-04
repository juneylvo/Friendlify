package TestUsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.users.FriendProfile;
import entities.users.UserProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestUsersHelper {
    public static String workingDirectory = System.getProperty("user.dir");
    public static String resourcesDirectory = "%s/src/test/resources".formatted(workingDirectory);
    public static String sampleUsersJsonPath = "%s/sampleUsers.json".formatted(resourcesDirectory);

    private static final ObjectMapper mapper = new ObjectMapper();
    private static JsonNode jsonNode;

    public TestUsersHelper() {
        JsonNode jsonNode1;
        try {
            jsonNode1 = mapper.readTree(new File(sampleUsersJsonPath));
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

    public JSONArray createUserFriendsList(List<FriendProfile> friendProfiles) {
        JSONArray friends = new JSONArray();
        for (FriendProfile friendProfile : friendProfiles) {
            JSONObject friend = new JSONObject()
                    .put("id", friendProfile.getUserId())
                    .put("displayName", friendProfile.getUsername());
            friends.put(friend);
        }
        return friends;
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
