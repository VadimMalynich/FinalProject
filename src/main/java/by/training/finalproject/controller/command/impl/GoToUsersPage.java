package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import by.training.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GoToUsersPage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToUsersPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.setAttribute("page", "Controller?command=go_to_users_page");

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }

        try {
            List<User> usersList = userService.getAllUser();
            session.setAttribute("usersList", usersList);

        } catch (ServiceException e) {
            userLogger.error(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usersPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
