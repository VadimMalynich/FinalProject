package by.training.finalproject.controller.command.impl;

import by.training.finalproject.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class GoToSignInPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("page", "Controller?command=go_to_sign_in_page");
        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
        requestDispatcher.forward(request, response);
    }
}
