package interfaceAdapters.rating;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RateSongPresenter class.
 */
public class RateSongPresenterTest {

    private final RateSongPresenter presenter = new RateSongPresenter();

    @Test
    public void testPrepareSuccessMessage() {
        // Act checked
        String message = presenter.prepareSuccessMessage("Test Song");

        // Assert
        assertEquals("Your rating for 'Test Song' has been submitted successfully!", message);
    }

    @Test
    public void testPrepareErrorMessage() {
        // Act
        String message = presenter.prepareErrorMessage("Invalid score provided");

        // Assert
        assertEquals("An error occurred while submitting your rating: Invalid score provided", message);
    }
}
