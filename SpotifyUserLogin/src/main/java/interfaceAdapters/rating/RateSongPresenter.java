package interfaceAdapters.rating;


/**
 * Handles the preparation of success and error messages for the song rating process.
 */
public class RateSongPresenter {

    /**
     * Prepares a success message indicating that the rating was submitted successfully.
     *
     * @param songTitle the title of the song that was rated
     * @return a success message string
     */
    public String prepareSuccessMessage(String songTitle) {
        return "Your rating for '" + songTitle + "' has been submitted successfully!";
    }

    /**
     * Prepares an error message indicating that an issue occurred during the rating submission.
     *
     * @param errorReason the reason for the error
     * @return an error message string
     */
    public String prepareErrorMessage(String errorReason) {
        return "An error occurred while submitting your rating: " + errorReason;
    }
}

