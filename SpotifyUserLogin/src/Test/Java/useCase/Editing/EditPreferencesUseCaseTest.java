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
        try {
            // Arrange
            UserProfile userProfile = new UserProfile(mockInteractor);
            EditPreferencesUseCase useCase = new EditPreferencesUseCase(mockInteractor, userProfile);

            List<String> genres = List.of("rock", "jazz");
            List<String> artists = List.of("Artist A", "Artist B");

            // Act
            EditPreferencesResponse response = useCase.execute(genres, artists);

            // Assert
            assertTrue(response.isSuccess());
            assertEquals("Preferences updated successfully.", response.getMessage());
            assertEquals(genres, response.getUpdatedGenres());
            assertEquals(artists, response.getUpdatedArtists());
            assertEquals(genres, userProfile.getPreferredGenres());
            assertEquals(artists, userProfile.getPreferredArtists());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    public void testExecuteDynamicUpdate() {
        try {
            // Arrange
            UserProfile userProfile = new UserProfile(mockInteractor);
            EditPreferencesUseCase useCase = new EditPreferencesUseCase(mockInteractor, userProfile);

            // Act
            EditPreferencesResponse response = useCase.execute();

            // Assert
            assertTrue(response.isSuccess());
            assertEquals("Preferences updated successfully.", response.getMessage());
            assertEquals(List.of("pop"), userProfile.getPreferredGenres());
            assertEquals(List.of("Artist One"), userProfile.getPreferredArtists());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    public void testExecuteEmptyInputs() {
        try {
            // Arrange
            UserProfile userProfile = new UserProfile(mockInteractor);
            EditPreferencesUseCase useCase = new EditPreferencesUseCase(mockInteractor, userProfile);

            // Act
            EditPreferencesResponse response = useCase.execute(List.of(), List.of());

            // Assert
            assertFalse(response.isSuccess());
            assertEquals("Genres and artists cannot both be empty.", response.getMessage());
            assertNull(response.getUpdatedGenres());
            assertNull(response.getUpdatedArtists());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    public void testExecuteDynamicUpdateEmptyPlaylists() {
        try {
            // Arrange: Mock SpotifyInteractor with empty playlist data
            SpotifyInteractor emptyPlaylistsInteractor = new SpotifyInteractor() {
                @Override
                public JSONObject getCurrentUserPlaylists(int limit, int offset) {
                    JSONObject playlistJson = new JSONObject();
                    playlistJson.put("items", new JSONArray()); // No playlists
                    return playlistJson;
                }
            };

            UserProfile userProfile = new UserProfile(emptyPlaylistsInteractor);
            EditPreferencesUseCase useCase = new EditPreferencesUseCase(emptyPlaylistsInteractor, userProfile);

            // Act
            EditPreferencesResponse response = useCase.execute();

            // Assert
            assertFalse(response.isSuccess());
            assertEquals("No playlists found.", response.getMessage());
            assertNull(response.getUpdatedGenres());
            assertNull(response.getUpdatedArtists());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    public void testExecuteDynamicUpdateNullPlaylists() {
        try {
            // Arrange: Mock SpotifyInteractor with null playlist data
            SpotifyInteractor nullPlaylistsInteractor = new SpotifyInteractor() {
                @Override
                public JSONObject getCurrentUserPlaylists(int limit, int offset) {
                    return null; // No playlist data
                }
            };

            UserProfile userProfile = new UserProfile(nullPlaylistsInteractor);
            EditPreferencesUseCase useCase = new EditPreferencesUseCase(nullPlaylistsInteractor, userProfile);

            // Act
            EditPreferencesResponse response = useCase.execute();

            // Assert
            assertFalse(response.isSuccess());
            assertEquals("No playlists found.", response.getMessage());
            assertNull(response.getUpdatedGenres());
            assertNull(response.getUpdatedArtists());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    public void testInvalidSpotifyData() {
        try {
            // Arrange: Mock SpotifyInteractor with invalid data
            SpotifyInteractor invalidInteractor = new SpotifyInteractor() {
                @Override
                public JSONObject getCurrentUserPlaylists(int limit, int offset) {
                    return null; // Simulate missing data
                }

                @Override
                public JSONObject getArtist(String artistId) {
                    return null; // No artist data
                }
            };

            UserProfile userProfile = new UserProfile(invalidInteractor);
            EditPreferencesUseCase useCase = new EditPreferencesUseCase(invalidInteractor, userProfile);

            // Act
            EditPreferencesResponse response = useCase.execute();

            // Assert
            assertFalse(response.isSuccess());
            assertEquals("Error updating preferences dynamically: Spotify data unavailable", response.getMessage());
            assertNull(response.getUpdatedGenres());
            assertNull(response.getUpdatedArtists());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
}
