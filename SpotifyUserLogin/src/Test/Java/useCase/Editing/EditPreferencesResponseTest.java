package useCase.Editing;

import junit.framework.TestCase;

import java.util.List;

public class EditPreferencesResponseTest extends TestCase {

    public void testIsSuccess() {
        // Test for success being true
        EditPreferencesResponse successResponse = new EditPreferencesResponse(
                true, "Operation succeeded", List.of("rock", "jazz"), List.of("Artist A", "Artist B")
        );
        assertTrue(successResponse.isSuccess());

        // Test for success being false
        EditPreferencesResponse failureResponse = new EditPreferencesResponse(
                false, "Operation failed", null, null
        );
        assertFalse(failureResponse.isSuccess());
    }

    public void testGetMessage() {
        // Test for a valid message
        EditPreferencesResponse responseWithMessage = new EditPreferencesResponse(
                true, "Preferences updated successfully.", null, null
        );
        assertEquals("Preferences updated successfully.", responseWithMessage.getMessage());

        // Test for a null message
        EditPreferencesResponse responseWithNullMessage = new EditPreferencesResponse(
                true, null, null, null
        );
        assertNull(responseWithNullMessage.getMessage());
    }

    public void testGetUpdatedGenres() {
        // Test when genres are provided
        EditPreferencesResponse responseWithGenres = new EditPreferencesResponse(
                true, "Genres updated", List.of("rock", "pop"), null
        );
        assertEquals(List.of("rock", "pop"), responseWithGenres.getUpdatedGenres());

        // Test when genres are null
        EditPreferencesResponse responseWithoutGenres = new EditPreferencesResponse(
                true, "No genres available", null, null
        );
        assertNull(responseWithoutGenres.getUpdatedGenres());

        // Test when genres are an empty list
        EditPreferencesResponse responseWithEmptyGenres = new EditPreferencesResponse(
                true, "Empty genres", List.of(), null
        );
        assertTrue(responseWithEmptyGenres.getUpdatedGenres().isEmpty());
    }

    public void testGetUpdatedArtists() {
        // Test when artists are provided
        EditPreferencesResponse responseWithArtists = new EditPreferencesResponse(
                true, "Artists updated", null, List.of("Artist A", "Artist B")
        );
        assertEquals(List.of("Artist A", "Artist B"), responseWithArtists.getUpdatedArtists());

        // Test when artists are null
        EditPreferencesResponse responseWithoutArtists = new EditPreferencesResponse(
                true, "No artists available", null, null
        );
        assertNull(responseWithoutArtists.getUpdatedArtists());

        // Test when artists are an empty list
        EditPreferencesResponse responseWithEmptyArtists = new EditPreferencesResponse(
                true, "Empty artists", null, List.of()
        );
        assertTrue(responseWithEmptyArtists.getUpdatedArtists().isEmpty());
    }

    public void testAllFields() {
        // Test with all fields populated
        EditPreferencesResponse completeResponse = new EditPreferencesResponse(
                true, "All fields set", List.of("pop", "rock"), List.of("Artist X", "Artist Y")
        );
        assertTrue(completeResponse.isSuccess());
        assertEquals("All fields set", completeResponse.getMessage());
        assertEquals(List.of("pop", "rock"), completeResponse.getUpdatedGenres());
        assertEquals(List.of("Artist X", "Artist Y"), completeResponse.getUpdatedArtists());
    }

    public void testEmptyFields() {
        // Test for empty genres and artists
        EditPreferencesResponse emptyResponse = new EditPreferencesResponse(
                false, "Empty fields", List.of(), List.of()
        );
        assertFalse(emptyResponse.isSuccess());
        assertEquals("Empty fields", emptyResponse.getMessage());
        assertTrue(emptyResponse.getUpdatedGenres().isEmpty());
        assertTrue(emptyResponse.getUpdatedArtists().isEmpty());
    }
}
