package by.training.finalproject.service.validation;

/**
 * Class for validate user data before add in database
 */
public class UserValidator {
    private static final String EMPTY_STRING = "";
    private static final String LOGIN_REGEX = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\w{6,20}$";
    private static final String PHONE_REGEX = "^\\+375([2][459]|33|44)[1-9]\\d{6}$";

    private UserValidator() {
    }

    /**
     * Method for validating user login
     *
     * @param login user login
     * @return {@code true} if {@code login} correct, {@code false} otherwise
     */
    public static boolean validateLogin(String login) {
        if (login == null || EMPTY_STRING.equals(login)) {
            return false;
        }
        return login.matches(LOGIN_REGEX);
    }

    /**
     * Method for validating user password
     *
     * @param password user password
     * @return {@code true} if {@code password} correct, {@code false} otherwise
     */
    public static boolean validatePassword(String password) {
        if (password == null || EMPTY_STRING.equals(password)) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

    /**
     * Method for validating username
     *
     * @param name username
     * @return {@code true} if {@code name} correct, {@code false} otherwise
     */
    public static boolean validateName(String name) {
        if (name == null || EMPTY_STRING.equals(name)) {
            return false;
        }
        return name.length() <= 23;
    }

    /**
     * Method for validating user phoneNumber
     *
     * @param phoneNumber user phoneNumber
     * @return {@code true} if {@code phoneNumber} correct, {@code false} otherwise
     */
    public static boolean validatePhone(String phoneNumber) {
        if (phoneNumber == null || EMPTY_STRING.equals(phoneNumber)) {
            return false;
        }
        return phoneNumber.matches(PHONE_REGEX);
    }

    /**
     * Method for validating city id
     *
     * @param cityId id of city
     * @return {@code true} if {@code cityId} correct, {@code false} otherwise
     */
    public static boolean validateCity(Integer cityId) {
        return (cityId == null || cityId < 1);
    }
}
