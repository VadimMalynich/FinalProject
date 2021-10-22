package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.AdInfoService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
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

public class GoToUserProfilePage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToUserProfilePage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("page", "Controller?command=go_to_user_profile_page");

        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }

        User user = (User) session.getAttribute("user");
        ServiceProvider provider = ServiceProvider.getInstance();
        AdInfoService adsInfoService = provider.getAdInfoService();

        try {
            List<AdInfo> adsInfoProfileList = adsInfoService.getUserAds(user.getId());
            session.setAttribute("userAdsProfileList", adsInfoProfileList);
        } catch (ServiceException e) {
            userLogger.error(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userProfile.jsp");
        requestDispatcher.forward(request, response);
    }
}
