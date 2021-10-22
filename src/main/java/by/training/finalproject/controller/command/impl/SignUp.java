package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import by.training.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class SignUp implements Command {
    private static final Logger userLogger = LogManager.getLogger(SignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("citiesList");
        String login = request.getParameter("signUpLogin");
        String password = request.getParameter("signUpPassword");
        String confirmPassword = request.getParameter("signUpConfirmPassword");
        String name = request.getParameter("signUpName");
        String phone = request.getParameter("signUpPhone");
        String messengers[] = request.getParameterValues("messengers[]");
        Integer cityId = Integer.valueOf(request.getParameter("signUpCity"));

        User user = new User(login, password, name, phone, cityId);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        try {
            userService.registration(user, confirmPassword, messengers);
            session.removeAttribute("citiesList");
            response.sendRedirect("Controller?command=go_to_home_page&message=message.signUp.complete");
        } catch (ServiceException e) {
            userLogger.info(e);
            response.sendRedirect("Controller?command=go_to_sign_up_page&message=message.error.signUp");
        }
    }
}
