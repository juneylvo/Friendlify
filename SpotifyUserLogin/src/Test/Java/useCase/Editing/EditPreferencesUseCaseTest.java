package useCase.Editing;

import api.SpotifyInteractor;
import entities.users.UserProfile;
import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class EditPreferencesUseCaseTest extends TestCase {

    private SpotifyInteractor mockInteractor;

    @Override
    protected void setUp() throws Exception {
        // Mock SpotifyInteractor with a valid implementation
        mockInteractor = new SpotifyInteractor() {
            @Override
            public JSONObject getCurrentUserPlaylists(int limit, int offset) {
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
                return new JSONObject().put("id", "test_user").put("displayName", "Test User");
            }

            @Override
            public JSONObject getArtist(String artistId) {
                return new JSONObject().put("genres", new JSONArray().put("pop"));
            }

            @Override
            public JSONObject getPlaylistItems(String playlistId, int limit, int offset) {
                JSONObject playlistItemsJson = new JSONObject();
                JSONArray tracks = new JSONArray();
                tracks.put(new JSONObject().put("track", new JSONObject()
                        .put("artists", new JSONArray().put(new JSONObject()
                                .put("id", "artist1")
                                .put("name", "Artist One")))));
                playlistItemsJson.put("items", tracks);
                return playlistItemsJson;
            }
        };
    }

    public void testExecuteManualUpdate() {
        // Test with genres and artists
        UserProfile userProfile = new UserProfile(mockInteractor);
        EditPreferencesUseCase useCase = new EditPreferencesUseCase(mockInteractor, userProfile);

        List<String> genres = List.of("rock", "jazz");
        List<String> artists = List.of("Artist A", "Artist B");

        EditPreferencesResponse response = useCase.execute(genres, artists);

        assertTrue(response.isSuccess());
        assertEquals("Preferences updated successfully.", response.getMessage());
        assertEquals(genres, response.getUpdatedGenres());
        assertEquals(artists, response.getUpdatedArtists());
    }

    public void testExecuteManualUpdateFailure() {
        // Test with empty genres and artists (invalid input)
        UserProfile userProfile = new UserProfile(mockInteractor);
        EditPreferencesUseCase useCase = new EditPreferencesUseCase(mockInteractor, userProfile);

        List<String> genres = List.of();
        List<String> artists = List.of();

        EditPreferencesResponse response = useCase.execute(genres, artists);

        assertFalse(response.isSuccess());
        assertEquals("Genres and artists cannot both be empty.", response.getMessage());
        assertNull(response.getUpdatedGenres());
        assertNull(response.getUpdatedArtists());
    }

    public void testExecuteDynamicUpdate() {
        // Test with valid playlists and tracks
        UserProfile userProfile = new UserProfile(mockInteractor);
        EditPreferencesUseCase useCase = new EditPreferencesUseCase(mockInteractor, userProfile);

        EditPreferencesResponse response = useCase.execute();

        assertTrue(response.isSuccess());
        assertEquals("Preferences updated successfully.", response.getMessage());
        assertEquals(List.of("pop"), userProfile.getPreferredGenres());
        assertEquals(List.of("Artist One"), userProfile.getPreferredArtists());
    }

    public void testExecuteDynamicUpdateFailure() {
        // Test with null playlists (API failure)
        SpotifyInteractor nullPlaylistsInteractor = new SpotifyInteractor() {
            @Override
            public JSONObject getCurrentUserPlaylists(int limit, int offset) {
                return null;
            }
        };

        UserProfile userProfile = new UserProfile(nullPlaylistsInteractor);
        EditPreferencesUseCase useCase = new EditPreferencesUseCase(nullPlaylistsInteractor, userProfile);

        EditPreferencesResponse response = useCase.execute();

        assertFalse(response.isSuccess());
        assertEquals("No playlists found.", response.getMessage());
        assertNull(response.getUpdatedGenres());
        assertNull(response.getUpdatedArtists());
    }

    public void testInvalidSpotifyData() {
        // Test with invalid Spotify data
        SpotifyInteractor invalidInteractor = new SpotifyInteractor() {
            @Override
            public JSONObject getArtist(String artistId) {
                return null;
            }
        };

        UserProfile userProfile = new UserProfile(invalidInteractor);
        EditPreferencesUseCase useCase = new EditPreferencesUseCase(invalidInteractor, userProfile);

        EditPreferencesResponse response = useCase.execute();

        assertTrue(response.isSuccess());
        assertEquals("Preferences updated dynamically.", response.getMessage());
        assertEquals(List.of("Unknown"), response.getUpdatedGenres());
    }
}
