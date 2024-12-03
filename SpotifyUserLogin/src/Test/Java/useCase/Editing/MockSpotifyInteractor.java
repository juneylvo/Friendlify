package useCase.Editing;

import api.SpotifyInteractor;
import org.json.JSONArray;
import org.json.JSONObject;

public class MockSpotifyInteractor extends SpotifyInteractor {

    @Override
    public JSONObject getCurrentUserPlaylists(int limit, int offset) {
        // Simulate a response with playlists
        JSONObject playlistJson = new JSONObject();
        JSONArray playlists = new JSONArray();
        playlists.put(new JSONObject()
                .put("id", "playlist1")
                .put("owner", new JSONObject()
                        .put("id", "friend1")
                        .put("displayName", "Friend One")));
        playlistJson.put("items", playlists);
        return playlistJson;
    }

    @Override
    public JSONObject getCurrentUserProfile() {
        // Simulate a user profile response
        return new JSONObject()
                .put("id", "test_user")
                .put("displayName", "Test User");
    }

    @Override
    public JSONObject getArtist(String artistId) {
        // Simulate artist data
        return new JSONObject()
                .put("genres", new JSONArray().put("pop"));
    }

    @Override
    public JSONObject getPlaylistItems(String playlistId, int limit, int offset) {
        // Simulate playlist items response
        JSONObject playlistItemsJson = new JSONObject();
        JSONArray tracks = new JSONArray();
        tracks.put(new JSONObject()
                .put("track", new JSONObject()
                        .put("artists", new JSONArray()
                                .put(new JSONObject()
                                        .put("id", "artist1")
                                        .put("name", "Artist One")))));
        playlistItemsJson.put("items", tracks);
        return playlistItemsJson;
    }
}
