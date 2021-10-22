package by.training.finalproject.service.validation;

/**
 * Class for validate comment data before add in database
 */
public class CommentValidator {
    private static final String EMPTY_STRING = "";

    private CommentValidator() {
    }

    /**
     * Method for validating user or ad id
     *
     * @param id id of user or ad
     * @return {@code true} if {@code id} correct, {@code false} otherwise
     */
    public static boolean validateId(Integer id) {
        return id == null || id < 1;
    }

    /**
     * Method for validating comment text
     *
     * @param text comment text
     * @return {@code true} if {@code text} correct, {@code false} otherwise
     */
    public static boolean validateText(String text) {
        if (text == null || EMPTY_STRING.equals(text)) {
            return false;
        }
        return text.length() < 200;
    }
}
