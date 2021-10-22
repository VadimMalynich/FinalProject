package by.training.finalproject.controller.command.impl;

import by.training.finalproject.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class Logout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String locale;
        if (session.getAttribute("locale") == null) {
            locale = Locale.getDefault().toString();
        } else {
            locale = (String) session.getAttribute("locale");
        }

        session.invalidate();

        response.sendRedirect("Controller?command=go_to_home_page&message=message.logout.complete&locale=" + locale);
    }
}
