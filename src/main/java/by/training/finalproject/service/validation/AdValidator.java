package by.training.finalproject.service.validation;

/**
 * Class for validate ad info before add in database
 */
public class AdValidator {
    private static final String EMPTY_STRING = "";
    private static final String MATERIAL_REGEX = "^[A-Za-z]{3,}([(]\\d{1,3}[%][)])?([,]\\s[A-Za-z]{3,}([(]\\d{1,2}[%][)])?)*$";

    private AdValidator() {
    }

    /**
     * Method for validating ad topic
     *
     * @param topic topic of ad
     * @return {@code true} if {@code topic} correct, {@code false} otherwise
     */
    public static boolean validateTopic(String topic) {
        if (topic == null || EMPTY_STRING.equals(topic)) {
            return false;
        }
        return topic.length() <= 50;
    }

    /**
     * Method for validating ad material
     *
     * @param material material of ad
     * @return {@code true} if {@code material} correct, {@code false} otherwise
     */
    public static boolean validateMaterialPattern(String material) {
        if (material == null || EMPTY_STRING.equals(material)) {
            return false;
        }
        return material.length() <= 50;
//        return material.matches(MATERIAL_REGEX);
    }

    /**
     * Method for validating ad material
     *
     * @param material material of ad
     * @return {@code true} if {@code material} correct, {@code false} otherwise
     */
    public static boolean validateMaterial(String material) {
        if (material == null || EMPTY_STRING.equals(material)) {
            return false;
        }
        return material.length() <= 50;
    }

    /**
     * Method for validating ad description
     *
     * @param description material of ad
     * @return {@code true} if {@code description} correct, {@code false} otherwise
     */
    public static boolean validateDescription(String description) {
        if (description == null || EMPTY_STRING.equals(description)) {
            return false;
        }
        return description.length() <= 500;
    }

    /**
     * Method for validating ad picture name
     *
     * @param picture name of photo of ad
     * @return {@code true} if {@code picture} correct, {@code false} otherwise
     */
    public static boolean validatePicture(String picture) {
        if (picture == null) {
            return true;
        }
        if (EMPTY_STRING.equals(picture)) {
            return false;
        }
        return picture.length() <= 260;
    }
}
