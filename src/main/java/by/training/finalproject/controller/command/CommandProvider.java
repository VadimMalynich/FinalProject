package by.training.finalproject.controller.command;

import by.training.finalproject.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
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
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }
}