package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.AdInfoService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class DeleteAdInfo implements Command {
    private static final Logger userLogger = LogManager.getLogger(DeleteAdInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ServiceProvider provider = ServiceProvider.getInstance();
        Integer integer = Integer.valueOf(request.getParameter("deleteAdIdInfo"));
        User user = (User) session.getAttribute("user");

        AdInfoService adsInfoService = provider.getAdInfoService();

        try {
            adsInfoService.delete(integer);
            if (user.getRole().getValue() == 0) {
                response.sendRedirect("Controller?command=go_to_home_page&message=message.deleteAd.complete");
            } else {
                response.sendRedirect("Controller?command=go_to_user_profile_page&message=message.deleteAd.complete");
            }
        } catch (ServiceException e) {
            userLogger.error(e);
            if (user.getRole().getValue() == 0) {
                response.sendRedirect("Controller?command=go_to_home_page&message=message.delete.unsuccessfully");
            } else {
                response.sendRedirect("Controller?command=go_to_user_profile_page&message=message.delete.unsuccessfully");
            }
        }
    }
}
