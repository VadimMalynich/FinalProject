package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.User;
import by.training.finalproject.bean.UserRole;
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

public class EditUser implements Command {
    private static final Logger userLogger = LogManager.getLogger(EditUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User previousUser = (User) session.getAttribute("editUser");
        User sessionUser = (User) session.getAttribute("user");

        previousUser.setName(request.getParameter("editName"));
        if (request.getParameter("editRole") != null) {
            previousUser.setRole(UserRole.getByCode(Integer.valueOf(request.getParameter("editRole"))));
        }
        previousUser.setPhoneNumber(request.getParameter("editPhone"));
        String messengers[] = request.getParameterValues("editMessengers[]");
        previousUser.getCity().setId(Integer.valueOf(request.getParameter("editCity")));

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        if (!"".equals(request.getParameter("editOldPassword")) && !"".equals(request.getParameter("editNewPassword"))) {
            String oldPass = request.getParameter("editOldPassword");
            String newPass = request.getParameter("editNewPassword");
            try {
                userService.edit(previousUser, messengers, oldPass, newPass);
            } catch (ServiceException e) {
                userLogger.info(e);
                if ("Entered wrong old password".equals(e.getMessage())) {
                    response.sendRedirect("Controller?command=go_to_edit_user_page&message.editUser.unsuccessfully");
                } else {
                    response.sendRedirect("Controller?command=go_to_edit_user_page&message=message.edit.unsuccessfully");
                }
            }
        } else {
            try {
                userService.edit(previousUser, messengers);
            } catch (ServiceException e) {
                userLogger.error(e);
                response.sendRedirect("Controller?command=go_to_edit_user_page&message=message.edit.unsuccessfully");
            }
        }
        session.removeAttribute("citiesList");
        session.removeAttribute("editUser");
        session.removeAttribute("userId");
        if (sessionUser.getRole().getValue() == 0) {
            response.sendRedirect("Controller?command=go_to_users_page&message=message.editUser.complete");
        } else {
            try {
                User user = userService.getUser(sessionUser.getId());
                session.setAttribute("user", user);
            } catch (ServiceException e) {
                userLogger.error(e);
            }
            response.sendRedirect("Controller?command=go_to_user_profile_page&message=message.editUser.complete");
        }
    }
}
