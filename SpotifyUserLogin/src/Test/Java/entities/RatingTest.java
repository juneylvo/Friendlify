package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Rating class.
 */
public class RatingTest {

    @Test
    public void testConstructor_ValidInputs() {
        // Arrange
        String userId = "user123";
        int score = 4;
        String comment = "Great song!";

        // Act
        Rating rating = new Rating(userId, score, comment);

        // Assert
        assertEquals(userId, rating.getUserId());
        assertEquals(score, rating.getScore());
        assertEquals(comment, rating.getComment());
    }

    @Test
    public void testConstructor_NullComment() {
        // Arrange
        String userId = "user123";
        int score = 3;

        // Act
        Rating rating = new Rating(userId, score, null);

        // Assert
        assertEquals(userId, rating.getUserId());
        assertEquals(score, rating.getScore());
        assertNull(rating.getComment());
    }

    @Test
    public void testSetScore_ValidScore() {
        // Arrange
        Rating rating = new Rating("user123", 3, "Nice song.");

        // Act
        rating.setScore(5);

        // Assert
        assertEquals(5, rating.getScore());
    }

    @Test
    public void testSetComment_ValidComment() {
        // Arrange
        Rating rating = new Rating("user123", 4, "Good song.");

        // Act
        rating.setComment("Amazing song!");

        // Assert
        assertEquals("Amazing song!", rating.getComment());
    }

    @Test
    public void testSetComment_NullComment() {
        // Arrange
        Rating rating = new Rating("user123", 4, "Good song.");

        // Act
        rating.setComment(null);

        // Assert
        assertNull(rating.getComment());
    }
}
