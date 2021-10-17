package by.training.finalproject.service.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommentValidatorTest {

    @DataProvider(name = "testValidateIdPositiveProvider")
    public Object[][] createDataForValidateIdPositive() {
        return new Object[][]{
                {5},
                {1},
                {33},
                {10225}
        };
    }

    @DataProvider(name = "testValidateIdNegativeProvider")
    public Object[][] createDataForValidateIdNegative() {
        return new Object[][]{
                {null},
                {0},
                {-5485},
                {-25}
        };
    }

    @DataProvider(name = "testValidateTextPositiveProvider")
    public Object[][] createDataForValidateCommentTextPositive() {
        return new Object[][]{
                {"Topic of this ad"},
                {"Good T-shirt"},
                {"Limited version of this trousers"},
                {"dwdwfew5d4sa649 as6 4d8wq@#@# we23rwd|ASD|qsad"}
        };
    }

    @DataProvider(name = "testValidateTextNegativeProvider")
    public Object[][] createDataForValidateCommentTextNegative() {
        return new Object[][]{
                {null},
                {""},
                {"6asd4s9a 8d456 sa4d8wq98d6as5dsadsadgy uasadashdhasjkdh asdhjaskdaksj dhasdashjd Limited5d454ad8sa84 sa54d 8as6das4d 6as4dwq98 6a4sd6 49as8d4 as4d9 8qwd456s4d9a8s4 8as4d45as4d5as4d7qw8d5as4d8sa8d54asdqw84d5asd87a5 version of this trousers"},
        };
    }


    @Test(description = "Positive scenario of validating id", dataProvider = "testValidateIdPositiveProvider")
    public void testValidateIdPositive(Integer id) {
        assertFalse(CommentValidator.validateId(id));
    }

    @Test(description = "Negative scenario of validating id", dataProvider = "testValidateIdNegativeProvider")
    public void testValidateIdNegative(Integer id) {
        assertTrue(CommentValidator.validateId(id));
    }

    @Test(description = "Positive scenario of validating comment text", dataProvider = "testValidateTextPositiveProvider")
    public void testValidateTextPositive(String text) {
        assertTrue(CommentValidator.validateText(text));
    }

    @Test(description = "Negative scenario of validating comment text", dataProvider = "testValidateTextNegativeProvider")
    public void testValidateTextNegative(String text) {
        assertFalse(CommentValidator.validateText(text));
    }
}