package by.training.finalproject.service.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {
    @DataProvider(name = "testValidateLoginPositiveProvider")
    public Object[][] createDataForValidateLoginPositive() {
        return new Object[][]{
                {"example@gmail.com"},
                {"egor_artyomov@edu.bsuir.by"},
                {"vadim.malynich_144563@mail.ru"},
                {"dwSAs.dsdwdWAad.dwakdDDWjfw@fasdw.dwasdsw.ass"}
        };
    }

    @DataProvider(name = "testValidateLoginNegativeProvider")
    public Object[][] createDataForValidateLoginNegative() {
        return new Object[][]{
                {""},
                {null},
                {"example@gmail"},
                {"egor_artyomovedu.bsuir.by"},
                {"wdsadv@aaa.a"},
                {"sa54s21ad5w845a"}
        };
    }

    @DataProvider(name = "testValidatePasswordPositiveProvider")
    public Object[][] createDataForValidatePasswordPositive() {
        return new Object[][]{
                {"215aswsaomAdws"},
                {"qwerty12345678ASDFGH"},
                {"21574d4755A"},
                {"Mountain21"}
        };
    }

    @DataProvider(name = "testValidatePasswordNegativeProvider")
    public Object[][] createDataForValidatePasswordNegative() {
        return new Object[][]{
                {""},
                {null},
                {"dw5S"},
                {"dsjahdkj548DDWAUHJKdw5a1dw5d"},
                {"5421521356"},
                {"dwqhdjasdjhwjk"},
                {"3a1sd6w"},
                {"DWKDAWJFASF"},
                {"35DWDSAD"},
                {"!@##!@$asdl55Da"},
                {"ASDWADAdaffwe"},
                {"wqjkdlwqkdlas5"}
        };
    }

    @DataProvider(name = "testValidateNamePositiveProvider")
    public Object[][] createDataForValidateNamePositive() {
        return new Object[][]{
                {"Aleksey"},
                {"Sasha"},
                {"Ян"},
                {"Алёна"}
        };
    }

    @DataProvider(name = "testValidateNameNegativeProvider")
    public Object[][] createDataForValidateNameNegative() {
        return new Object[][]{
                {""},
                {null},
                {"askjdnqfkelfkwkelfewkflwef;"},
        };
    }

    @DataProvider(name = "testValidatePhonePositiveProvider")
    public Object[][] createDataForValidatePhonePositive() {
        return new Object[][]{
                {"+375292057785"},
                {"+375245584235"},
                {"+375447782266"},
                {"+375259963254"},
                {"+375335484852"}
        };
    }

    @DataProvider(name = "testValidatePhoneNegativeProvider")
    public Object[][] createDataForValidatePhoneNegative() {
        return new Object[][]{
                {""},
                {null},
                {"+375206647825"},
                {"+375250255547"},
                {"375335484852"},
                {"dqwdsadwq"},
                {"+csacasdsadas"},
                {"+375292O57785"},
                {"+375265484554885"}
        };
    }

    @DataProvider(name = "testValidateCityPositiveProvider")
    public Object[][] createDataForValidateCityPositive() {
        return new Object[][]{
                {5},
                {1},
                {33},
                {10225}
        };
    }

    @DataProvider(name = "testValidateCityNegativeProvider")
    public Object[][] createDataForValidateCityNegative() {
        return new Object[][]{
                {null},
                {0},
                {-5485},
                {-25}
        };
    }

    @Test(testName = "Positive scenario of validating login", dataProvider = "testValidateLoginPositiveProvider")
    public void testValidateLoginPositive(String login) {
        assertTrue(UserValidator.validateLogin(login));
    }

    @Test(testName = "Negative scenario of validating login", dataProvider = "testValidateLoginNegativeProvider")
    public void testValidateLoginNegative(String login) {
        assertFalse(UserValidator.validateLogin(login));
    }

    @Test(testName = "Positive scenario of validating password", dataProvider = "testValidatePasswordPositiveProvider")
    public void testValidatePasswordPositive(String password) {
        assertTrue(UserValidator.validatePassword(password));
    }

    @Test(testName = "Negative scenario of validating password", dataProvider = "testValidatePasswordNegativeProvider")
    public void testValidatePasswordNegative(String password) {
        assertFalse(UserValidator.validatePassword(password));
    }

    @Test(testName = "Positive scenario of validating name", dataProvider = "testValidateNamePositiveProvider")
    public void testValidateNamePositive(String name) {
        assertTrue(UserValidator.validateName(name));
    }

    @Test(testName = "Negative scenario of validating name", dataProvider = "testValidateNameNegativeProvider")
    public void testValidateNameNegative(String name) {
        assertFalse(UserValidator.validateName(name));
    }

    @Test(testName = "Positive scenario of validating phone number", dataProvider = "testValidatePhonePositiveProvider")
    public void testValidatePhonePositive(String phoneNumber) {
        assertTrue(UserValidator.validatePhone(phoneNumber));
    }

    @Test(testName = "Negative scenario of validating phoneNumber", dataProvider = "testValidatePhoneNegativeProvider")
    public void testValidatePhoneNegative(String phoneNumber) {
        assertFalse(UserValidator.validatePhone(phoneNumber));
    }

    @Test(testName = "Positive scenario of validating city", dataProvider = "testValidateCityPositiveProvider")
    public void testValidateCityPositive(Integer cityId) {
        assertFalse(UserValidator.validateCity(cityId));
    }

    @Test(testName = "Negative scenario of validating city", dataProvider = "testValidateCityNegativeProvider")
    public void testValidateCityNegative(Integer cityId) {
        assertTrue(UserValidator.validateCity(cityId));
    }
}