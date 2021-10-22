package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.Comments;
import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.*;
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

public class GoToAdPage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToAdPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("page", "Controller?command=go_to_ad_page&adIdInfo=" + request.getParameter("adIdInfo"));
        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }
        Integer integer = Integer.valueOf(request.getParameter("adIdInfo"));
        User userSession = (User) session.getAttribute("user");

        ServiceProvider provider = ServiceProvider.getInstance();
        AdInfoService adsInfoService = provider.getAdInfoService();
        CommentService commentService = provider.getCommentService();
        boolean flag;

        try {
            AdInfo adInfo = adsInfoService.getAdInfo(integer);
            if (adInfo == null) {
                response.sendRedirect("Controller?command=go_to_home_page&message=message.error.openAd");
            } else {
                if (userSession == null) {
                    flag = false;
                } else {
                    flag = adsInfoService.isLikePressed(adInfo.getId(), userSession.getId());
                }
                List<Comments> commentsList = commentService.getAll(adInfo.getId());
                session.setAttribute("commentsList", commentsList);
                session.setAttribute("isLike", flag);
                session.setAttribute("adPageInfo", adInfo);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adPage.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            userLogger.error(e);
            response.sendRedirect("Controller?command=go_to_home_page&message=message.error.server");
        }
    }
}
