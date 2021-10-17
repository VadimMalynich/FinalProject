package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.Comments;
import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.CommentService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddComment implements Command {
    private static final Logger userLogger = LogManager.getLogger(AddComment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdInfo adInfo = (AdInfo) session.getAttribute("adPageInfo");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Controller?command=go_to_ad_page&adIdInfo=" + adInfo.getId() + "&message=message.error.comment");
            return;
        }
        session.removeAttribute("commentsList");

        String commentText = request.getParameter("userComment");

        Comments comment = new Comments(adInfo.getId(), user.getId(), commentText);

        ServiceProvider provider = ServiceProvider.getInstance();
        CommentService commentService = provider.getCommentService();

        try {
            commentService.add(comment);
            response.sendRedirect("Controller?command=go_to_ad_page&adIdInfo=" + adInfo.getId() + "&message=message.addComment.complete");
        } catch (ServiceException e) {
            userLogger.info(e);
            if (e.getMessage().equals("Wrong comment text") || e.getMessage().equals("Wrong id")) {
                response.sendRedirect("Controller?command=go_to_ad_page&adIdInfo=" + adInfo.getId() + "&message=message.error.commentAdd");
            } else {
                response.sendRedirect("Controller?command=go_to_ad_page&adIdInfo=" + adInfo.getId() + "&message=message.error.server");
            }
        }
    }
}
