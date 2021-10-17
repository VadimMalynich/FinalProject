package by.training.finalproject.controller.command.impl;

import by.training.finalproject.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocale implements Command {
    private static final String PAGE = "page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("locale", request.getParameter("command"));

        String page;

        if (request.getParameter(PAGE) != null) {
            page = request.getParameter(PAGE);
        } else if (session.getAttribute(PAGE) != null) {
            page = (String) session.getAttribute(PAGE);
        } else {
            page = "index.jsp";
        }
        response.sendRedirect(page);
    }
}
