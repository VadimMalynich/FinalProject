package by.training.finalproject.controller.command.impl;

import by.training.finalproject.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");
        session.invalidate();

        response.sendRedirect("Controller?command=go_to_home_page&message=message.logout.complete&locale=" + locale);
    }
}
