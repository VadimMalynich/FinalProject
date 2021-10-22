package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.City;
import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.*;
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

public class GoToEditUserPage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToEditUserPage.class);
    private static final String USER_ID = "userId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("page", "Controller?command=go_to_edit_user_page");
        
        if (request.getParameter(USER_ID) != null) {
            session.setAttribute(USER_ID, Integer.valueOf(request.getParameter(USER_ID)));
        }

        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }
        Integer userId = (Integer) session.getAttribute(USER_ID);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        try {
            User user = userService.getUser(userId);
            List<City> cities = userService.getAllCities();
            session.setAttribute("citiesList", cities);
            session.setAttribute("editUser", user);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editUserPage.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            userLogger.error(e);
            response.sendRedirect("Controller?command=go_to_users_page&message=message.error.server");
        }
    }
}
