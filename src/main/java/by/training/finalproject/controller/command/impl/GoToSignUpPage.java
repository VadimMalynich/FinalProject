package by.training.finalproject.controller.command.impl;


import by.training.finalproject.bean.City;
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
import java.io.IOException;
import java.util.List;

public class GoToSignUpPage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToSignUpPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("page", "Controller?command=go_to_sign_up_page");
        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }

        ServiceProvider service = ServiceProvider.getInstance();
        UserService userService = service.getUserService();
        try {
            List<City> cities = userService.getAllCities();
            session.setAttribute("citiesList", cities);
        } catch (ServiceException e) {
            userLogger.error(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
        requestDispatcher.forward(request, response);
    }
}
