package interfaceAdapters.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import useCase.Rating.RateSongUseCase;
import entities.Song;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RateSongController class.
 */
public class RateSongControllerTest {

    private Map<String, Song> songRepository;

    @BeforeEach
    public void setUp() {
        RateSongUseCase rateSongUseCase = new RateSongUseCase(); // Use a real instance of RateSongUseCase
        songRepository = new HashMap<>();
        RateSongController rateSongController = new RateSongController(rateSongUseCase, songRepository);
    }

    @Test
    public void testRateSong_ValidSong() {
        // Arrange
        Song song = new Song("1", "Test Song", Collections.singletonList("Test Artist"));
        songRepository.put("1", song);

        // Act
        RateSongController.rateSong("1", "user123", 5, "Great song!");

        // Assert
        assertEquals(0, song.getRatingForUser("user123"));
    }

    @Test
    public void testRateSong_InvalidSongId() {
        // Arrange clearly
        String invalidSongId = "999";

        // Act
        RateSongController.rateSong(invalidSongId, "user123", 4, "Nice song!");

        // Assert
        assertNull(songRepository.get(invalidSongId));
    }

    @Test
    public void testRateSong_InvalidScore() {
        // Arrange
        Song song = new Song("1", "Test Song", Collections.singletonList("Test Artist"));
        songRepository.put("1", song);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> RateSongController.rateSong("1", "user123", 6, "Too good!"));
    }

    @Test
    public void testRateSong_ValidWithoutComment() {
        // Arrange
        Song song = new Song("2", "Another Song", Collections.singletonList("Another Artist"));
        songRepository.put("2", song);

        // Act
        RateSongController.rateSong("2", "user456", 3, null);

        // Assert
        assertEquals(0, song.getRatingForUser("user456"));
        assertNull(song.getCommentForUser("user456"));
    }
}