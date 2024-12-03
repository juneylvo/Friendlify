package useCase.Rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Rating;
import entities.Song;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RateSongUseCase class.
 */
public class RateSongUseCaseTest {

    private RateSongUseCase rateSongUseCase;
    private Song testSong;

    @BeforeEach
    public void setUp() {
        rateSongUseCase = new RateSongUseCase();
        testSong = new Song("1", "Test Song", Collections.singletonList("Test Artist"));
    }

    @Test
    public void testRateSong_ValidInputsNewRating() {
        // Act
        rateSongUseCase.rateSong(testSong, "user123", 5, "Excellent track!");

        // Assert
        List<Rating> ratings = testSong.getRatings();
        assertEquals(1, ratings.size());
        assertEquals("user123", ratings.get(0).getUserId());
        assertEquals(5, ratings.get(0).getScore());
        assertEquals("Excellent track!", ratings.get(0).getComment());
    }

    @Test
    public void testRateSong_UpdateExistingRating() {
        // Arrange
        testSong.addRating(new Rating("user123", 4, "Good track"));

        // Act
        rateSongUseCase.rateSong(testSong, "user123", 5, "Amazing track!");

        // Assert
        List<Rating> ratings = testSong.getRatings();
        assertEquals(1, ratings.size());
        assertEquals(5, ratings.get(0).getScore());
        assertEquals("Amazing track!", ratings.get(0).getComment());
    }

    @Test
    public void testRateSong_InvalidScore() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> rateSongUseCase.rateSong(testSong, "user123", 6, "Too good!"));
        assertThrows(IllegalArgumentException.class, () -> rateSongUseCase.rateSong(testSong, "user123", 0, "Too bad!"));
    }

    @Test
    public void testRateSong_CommentTooLong() {
        // Arrange
        String longComment = "a".repeat(501);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> rateSongUseCase.rateSong(testSong, "user123", 4, longComment));
    }

    @Test
    public void testGetAllRatings_ValidSong() {
        // Arrange
        testSong.addRating(new Rating("user1", 5, "Great!"));
        testSong.addRating(new Rating("user2", 3, "Okay"));

        // Act
        List<Rating> ratings = rateSongUseCase.getAllRatings(testSong);

        // Assert
        assertEquals(2, ratings.size());
        assertEquals("user1", ratings.get(0).getUserId());
        assertEquals("user2", ratings.get(1).getUserId());
    }

    @Test
    public void testGetAllRatings_NullSong() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> rateSongUseCase.getAllRatings(null));
    }
}
