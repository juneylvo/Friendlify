package useCase.Editing;

import junit.framework.TestCase;

import java.util.List;

public class EditPreferencesResponseTest extends TestCase {

    public void testIsSuccess() {
        // Test when success is true
        EditPreferencesResponse successResponse = new EditPreferencesResponse(
                true, null, List.of("rock", "pop"), List.of("Artist A")
        );
        assertTrue(successResponse.isSuccess());

        // Test when success is false
        EditPreferencesResponse failureResponse = new EditPreferencesResponse(
                false, "Operation failed", null, null
        );
        assertFalse(failureResponse.isSuccess());
    }

    public void testGetMessage() {
        // Test default success message
        EditPreferencesResponse successResponse = new EditPreferencesResponse(
                true, null, List.of("rock"), List.of("Artist A")
        );
        assertEquals("Preferences updated successfully.", successResponse.getMessage());

        // Test custom error message
        EditPreferencesResponse failureResponse = new EditPreferencesResponse(
                false, "Custom error message", null, null
        );
        assertEquals("Custom error message", failureResponse.getMessage());

        // Test null error message
        EditPreferencesResponse nullMessageResponse = new EditPreferencesResponse(
                false, null, null, null
        );
        assertNull(nullMessageResponse.getMessage());
    }

    public void testGetUpdatedGenres() {
        // Test when updated genres are provided
        EditPreferencesResponse responseWithGenres = new EditPreferencesResponse(
                true, null, List.of("rock", "pop"), null
        );
        assertEquals(List.of("rock", "pop"), responseWithGenres.getUpdatedGenres());

        // Test when updated genres are null
        EditPreferencesResponse responseWithoutGenres = new EditPreferencesResponse(
                true, null, null, null
        );
        assertNull(responseWithoutGenres.getUpdatedGenres());

        // Test when updated genres are empty
        EditPreferencesResponse responseWithEmptyGenres = new EditPreferencesResponse(
                true, null, List.of(), null
        );
        assertTrue(responseWithEmptyGenres.getUpdatedGenres().isEmpty());
    }

    public void testGetUpdatedArtists() {
        // Test when updated artists are provided
        EditPreferencesResponse responseWithArtists = new EditPreferencesResponse(
                true, null, null, List.of("Artist A", "Artist B")
        );
        assertEquals(List.of("Artist A", "Artist B"), responseWithArtists.getUpdatedArtists());

        // Test when updated artists are null
        EditPreferencesResponse responseWithoutArtists = new EditPreferencesResponse(
                true, null, null, null
        );
        assertNull(responseWithoutArtists.getUpdatedArtists());

        // Test when updated artists are empty
        EditPreferencesResponse responseWithEmptyArtists = new EditPreferencesResponse(
                true, null, null, List.of()
        );
        assertTrue(responseWithEmptyArtists.getUpdatedArtists().isEmpty());
    }

    public void testAllFields() {
        // Test when all fields are populated
        EditPreferencesResponse completeResponse = new EditPreferencesResponse(
                true, null, List.of("rock", "pop"), List.of("Artist A", "Artist B")
        );
        assertTrue(completeResponse.isSuccess());
        assertEquals("Preferences updated successfully.", completeResponse.getMessage());
        assertEquals(List.of("rock", "pop"), completeResponse.getUpdatedGenres());
        assertEquals(List.of("Artist A", "Artist B"), completeResponse.getUpdatedArtists());
    }

    public void testEmptyFields() {
        // Test when genres and artists are both empty
        EditPreferencesResponse emptyResponse = new EditPreferencesResponse(
                false, "Empty fields", List.of(), List.of()
        );
        assertFalse(emptyResponse.isSuccess());
        assertEquals("Empty fields", emptyResponse.getMessage());
        assertTrue(emptyResponse.getUpdatedGenres().isEmpty());
        assertTrue(emptyResponse.getUpdatedArtists().isEmpty());
    }

    public void testNullFields() {
        // Test when genres and artists are null
        EditPreferencesResponse nullResponse = new EditPreferencesResponse(
                false, "Null fields", null, null
        );
        assertFalse(nullResponse.isSuccess());
        assertEquals("Null fields", nullResponse.getMessage());
        assertNull(nullResponse.getUpdatedGenres());
        assertNull(nullResponse.getUpdatedArtists());
    }
}
