package by.training.finalproject.controller.filter;

import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.CommandName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecurityFilter implements Filter {
    private static final Logger userLogger = LogManager.getLogger(SecurityFilter.class);

    private static List<CommandName> commonCommands = new ArrayList<>();
    private static List<CommandName> guestCommands = new ArrayList<>();
    private static List<CommandName> commonUsersCommands = new ArrayList<>();
    private static List<CommandName> usersCommands = new ArrayList<>();
    private static List<CommandName> adminCommands = new ArrayList<>();


    static {
        commonCommands.add(CommandName.GO_TO_HOME_PAGE);
        commonCommands.add(CommandName.EN_US);
        commonCommands.add(CommandName.RU_RU);
        commonCommands.add(CommandName.GO_TO_AD_PAGE);
        commonCommands.add(CommandName.SEARCH_ADS);
        commonCommands.add(CommandName.FILTER_ADS);
        commonCommands.add(CommandName.FILTER_ADS);
        commonCommands.add(CommandName.WRONG_REQUEST);

        guestCommands.add(CommandName.SIGN_IN);
        guestCommands.add(CommandName.SIGN_UP);
        guestCommands.add(CommandName.GO_TO_SIGN_UP_PAGE);
        guestCommands.add(CommandName.GO_TO_SIGN_IN_PAGE);

        commonUsersCommands.add(CommandName.LOGOUT);
        commonUsersCommands.add(CommandName.ADD_COMMENT);
        commonUsersCommands.add(CommandName.DELETE_COMMENT);
        commonUsersCommands.add(CommandName.LIKE_AD);
        commonUsersCommands.add(CommandName.DELETE_AD);
        commonUsersCommands.add(CommandName.GO_TO_EDIT_AD_PAGE);
        commonUsersCommands.add(CommandName.EDIT_AD);
        commonUsersCommands.add(CommandName.GO_TO_EDIT_USER_PAGE);
        commonUsersCommands.add(CommandName.EDIT_USER);

        usersCommands.add(CommandName.GO_TO_ADD_AD_PAGE);
        usersCommands.add(CommandName.ADD_AD);
        usersCommands.add(CommandName.GO_TO_USER_PROFILE_PAGE);

        adminCommands.add(CommandName.ADD_CATEGORY);
        adminCommands.add(CommandName.DELETE_CATEGORY);
        adminCommands.add(CommandName.EDIT_CATEGORY);
        adminCommands.add(CommandName.GO_TO_TYPES_PAGE);
        adminCommands.add(CommandName.GO_TO_USERS_PAGE);
        adminCommands.add(CommandName.DELETE_USER);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession();
            User user = (User) session.getAttribute("user");
            boolean flag = false;
            String command = request.getParameter("command");
            if (command == null) {
                chain.doFilter(request, response);
            }
            if (user == null && !commonCommands.contains(getCommand(command)) && !guestCommands.contains(getCommand(command))) {
                userLogger.debug("Denied guest access");
                flag = true;
            }
            if (user != null) {
                switch (user.getRole()) {
                    case ADMINISTRATOR:
                        if (!commonUsersCommands.contains(getCommand(command)) && !adminCommands.contains(getCommand(command))
                                && !commonCommands.contains(getCommand(command))) {
                            userLogger.debug("Denied admin access");
                            flag = true;
                        }
                        break;
                    case USER:
                        if (commonUsersCommands.contains(getCommand(command)) && usersCommands.contains(getCommand(command))
                                && !commonCommands.contains(getCommand(command))) {
                            userLogger.debug("Denied user access");
                            flag = true;
                        }
                        break;
                    default:
                        userLogger.debug("Unknown user role");
                        flag = true;
                        break;
                }
            }
            if (!flag) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect("Controller?command=go_to_home_page&message=message.error.access");
            }
        } else {
            userLogger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private CommandName getCommand(String command) {
        try {
            return CommandName.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            String errorMessage = "Wrong command: " + command;
            userLogger.debug(errorMessage);
            return CommandName.WRONG_REQUEST;
        }
    }
}
