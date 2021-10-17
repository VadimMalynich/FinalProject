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
import java.io.IOException;

public class LikeAd implements Command {
    private static final Logger userLogger = LogManager.getLogger(LikeAd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("isLike") != null) {
            session.removeAttribute("isLike");
        }
        AdInfo adInfo = (AdInfo) session.getAttribute("adPageInfo");
        User user = (User) session.getAttribute("user");

        ServiceProvider provider = ServiceProvider.getInstance();
        AdInfoService adInfoService = provider.getAdInfoService();
        try {
            adInfoService.changeLikesCounter(adInfo.getId(), user.getId());
            response.sendRedirect("Controller?command=go_to_ad_page&adIdInfo=" + adInfo.getId());
        } catch (ServiceException e) {
            userLogger.error(e);
            response.sendRedirect("Controller?command=go_to_home_page&message=message.error.server");
        }
    }
}
