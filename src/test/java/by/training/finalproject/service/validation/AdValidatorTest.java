package by.training.finalproject.service.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AdValidatorTest {

    @DataProvider(name = "testValidateTopicPositiveProvider")
    public Object[][] createDataForValidateTopicPositive() {
        return new Object[][]{
                {"Topic of this ad"},
                {"Good T-shirt"},
                {"Limited version of this trousers"},
                {"New balance 840"}
        };
    }

    @DataProvider(name = "testValidateTopicNegativeProvider")
    public Object[][] createDataForValidateTopicNegative() {
        return new Object[][]{
                {""},
                {null},
                {"sadkjhkjwelfdfkwoijeflkkn wjeklfn bwehf la hfoewijfkwejfwy kjsdf54wf 8w"},
                {"dasdiiuwyfu  454as48dwq4d5asd asd 54wq8d 55as45 8dwq "}
        };
    }

    @DataProvider(name = "testValidateMaterialPositiveProvider")
    public Object[][] createDataForValidateMaterialPositive() {
        return new Object[][]{
                {"flax"},
                {"flax(100%)"},
                {"flax(75%), cotton(25%)"},
                {"flax(80%), cotton"},
                {"flax(75%), cotton(20%), polyester(5%)"}
        };
    }

    @DataProvider(name = "testValidateMaterialNegativeProvider")
    public Object[][] createDataForValidateMaterialNegative() {
        return new Object[][]{
                {""},
                {null},
                {"flax("},
                {"flax(80%), cotton(100%)"},
                {"54856448"},
                {"flax(80%), "},
                {"flax80%"}
        };
    }

    @DataProvider(name = "testValidateDescriptionPositiveProvider")
    public Object[][] createDataForValidateDescriptionPositive() {
        return new Object[][]{
                {"Like new trousers. Sell because buy new."},
                {"Wait your calls"},
                {"all questions on mobile"},
                {"daslodiu kwhjkld nwfhk ewbkfgyuwiofejwehfwe6f sd5f98 6s56wq5d 6as4 5d46qw849d46s4d 89wq46sa489d6sd48wq5d4as48das54d8as4 5as4d 8wq5d4a8s7w984d5a6s48d8adsajdytasuygdysaf sagyg fdyuqwdgyqud agsduy hsaytudgsagytdwghqdyuahdgsajhdg gdy uqwddqyfdsa gfdgashgdfasgdsagfdytgwhdgasdhg sah dasgdyttwqfytydaghsytudgashhgdashjkdashuifd kashdhg hjdasgdjwghqudhagsdhashgdyuasgvdghashdguyhasd gsajhgj kdasjjhdasgduyqwhjdausydkhasduyasdhga sgj dasjdhhgqwuihdhjadsakgjfhjkfbjasukhkdsbhsgahkdbwhga dwhh"},
                {"flax(75%), cotton(20%), polyester(5%)"}
        };
    }

    @DataProvider(name = "testValidateDescriptionNegativeProvider")
    public Object[][] createDataForValidateDescriptionNegative() {
        return new Object[][]{
                {""},
                {null},
                {"daslodiu kwhjkld nwfhk ewbkfgyuwiofejwehfwe6f sd5f98 6s56wq5d 6as4 5d46qw849d46s4d 89wq46sa489d6sd48wq5d4as48das54d8as4 5as4d 8wq5d4a8s7w984d5a6s48d8adsajdytasuygdysaf sagyg fdyuqwdgyqud agsduy hsaytudgsagytdwghqdyuahdgsajhdg gdy uqwddqyfdsa gfdgashgdfasgdsagfdytgwhdgasdhg sah dasgdyttwqfytydaghsytudgashhgdashjkdashuifd kashdhg hjdasgdjwghqudhagsdhashgdyuasgvdghashdguyhasd gsajhgj kdasjjhdasgduyqwhjdausydkhasduyasdhga sgj dasjdhhgqwuihdhjadsakgjfhjkfbjasukhkdsbhsgahkdbwhga dwhhasdhkasjkdl jasdhausjdasjhjdk ashkdnasj asjbhdasndh asdhashdjka sdvashukjdjasjjdksabjjhdujasnh hjsa jhdkj"},
        };
    }

    @DataProvider(name = "testValidatePicturePositiveProvider")
    public Object[][] createDataForValidatePicturePositive() {
        return new Object[][]{
                {"trousers1.png"},
                {"snickers.jpeg"},
                {"T-shirt_new.jpg"}
        };
    }

    @DataProvider(name = "testValidatePictureNegativeProvider")
    public Object[][] createDataForValidatePictureNegative() {
        return new Object[][]{
                {""},
                {null},
                {"daslodiu kwhjkld nwfhk ewbkfgyuwiofejwehfwe6f sd5f98 6s56wq5d 6as4 5d46qw849d46s4d 89wq46sa489d6sd48wq5d4as48das54d8as4 5as4d 8wq5d4a8s7w984d5a6s48d8adsajdytasuygdysaf sagyg fdyuqwdgyqud agsduy hsaytudgsagytdwghqdyuahdgsajhdg gdy uqwddqyfdsa wdahsjkdl lksad)DWQ)_@#_QW_= as"},
        };
    }

    @Test(testName = "Positive scenario of validating ad topic", dataProvider = "testValidateTopicPositiveProvider")
    public void testValidateTopicPositive(String topic) {
        assertTrue(AdValidator.validateTopic(topic));
    }

    @Test(testName = "Negative scenario of validating ad topic", dataProvider = "testValidateTopicNegativeProvider")
    public void testValidateTopicNegative(String topic) {
        assertFalse(AdValidator.validateTopic(topic));
    }

    @Test(testName = "Positive scenario of validating ad material", dataProvider = "testValidateMaterialPositiveProvider")
    public void testValidateMaterialPositive(String material) {
        assertTrue(AdValidator.validateMaterialPattern(material));
    }

    @Test(testName = "Negative scenario of validating ad material", dataProvider = "testValidateMaterialNegativeProvider")
    public void testValidateMaterialNegative(String material) {
        assertFalse(AdValidator.validateMaterialPattern(material));
    }

    @Test(testName = "Positive scenario of validating ad description", dataProvider = "testValidateDescriptionPositiveProvider")
    public void testValidateDescriptionPositive(String description) {
        assertTrue(AdValidator.validateDescription(description));
    }

    @Test(testName = "Negative scenario of validating ad description", dataProvider = "testValidateDescriptionNegativeProvider")
    public void testValidateDescriptionNegative(String description) {
        assertFalse(AdValidator.validateDescription(description));
    }

    @Test(testName = "Positive scenario of validating ad picture", dataProvider = "testValidatePicturePositiveProvider")
    public void testValidatePicturePositive(String picturePath) {
        assertTrue(AdValidator.validatePicture(picturePath));
    }

    @Test(testName = "Negative scenario of validating ad picture", dataProvider = "testValidatePictureNegativeProvider")
    public void testValidatePictureNegative(String picturePath) {
        assertFalse(AdValidator.validatePicture(picturePath));
    }
}