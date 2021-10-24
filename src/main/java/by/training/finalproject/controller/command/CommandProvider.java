package by.training.finalproject.controller.command;

import by.training.finalproject.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final Logger userLogger = LogManager.getLogger(CommandProvider.class);

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GO_TO_SIGN_IN_PAGE, new GoToSignInPage());
        commands.put(CommandName.GO_TO_SIGN_UP_PAGE, new GoToSignUpPage());
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.GO_TO_HOME_PAGE, new GoToHomePage());
        commands.put(CommandName.EN_US, new ChangeLocale());
        commands.put(CommandName.RU_RU, new ChangeLocale());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GO_TO_AD_PAGE, new GoToAdPage());
        commands.put(CommandName.ADD_COMMENT, new AddComment());
        commands.put(CommandName.LIKE_AD, new LikeAd());
        commands.put(CommandName.GO_TO_ADD_AD_PAGE, new GoToAddAdPage());
        commands.put(CommandName.ADD_AD, new AddAdInfo());
        commands.put(CommandName.GO_TO_USER_PROFILE_PAGE, new GoToUserProfilePage());
        commands.put(CommandName.DELETE_AD, new DeleteAdInfo());
        commands.put(CommandName.GO_TO_EDIT_AD_PAGE, new GoToEditAddPage());
        commands.put(CommandName.EDIT_AD, new EditAdInfo());
        commands.put(CommandName.ADD_CATEGORY, new AddClothesType());
        commands.put(CommandName.DELETE_CATEGORY, new DeleteClothesType());
        commands.put(CommandName.EDIT_CATEGORY, new EditClothesType());
        commands.put(CommandName.GO_TO_TYPES_PAGE, new GoToTypesPage());
        commands.put(CommandName.GO_TO_USERS_PAGE, new GoToUsersPage());
        commands.put(CommandName.DELETE_USER, new DeleteUser());
        commands.put(CommandName.GO_TO_EDIT_USER_PAGE, new GoToEditUserPage());
        commands.put(CommandName.EDIT_USER, new EditUser());
        commands.put(CommandName.DELETE_COMMENT, new DeleteComment());
        commands.put(CommandName.SEARCH_ADS, new SearchAds());
        commands.put(CommandName.FILTER_ADS, new FilterAdsByType());
        commands.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command takeCommand(String name) {
        CommandName commandName;
        Command command;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = commands.get(commandName);
        }catch (IllegalArgumentException | NullPointerException e){
            String errorMessage = "Wrong command: " + name;
            userLogger.debug(errorMessage);
            command = commands.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}