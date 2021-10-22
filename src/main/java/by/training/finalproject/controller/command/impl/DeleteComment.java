package by.training.finalproject.controller.command.impl;

import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.CommentService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteComment implements Command {
    private static final Logger userLogger = LogManager.getLogger(DeleteComment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        Integer adId = Integer.valueOf(request.getParameter("adIdInfo"));
        Integer id = Integer.valueOf(request.getParameter("commentId"));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        CommentService commentService = serviceProvider.getCommentService();

        try {
            commentService.delete(id);
            response.sendRedirect("Controller?command=go_to_ad_page&adIdInfo=" + adId + "&message=message.deleteComment.complete");
        } catch (ServiceException e) {
            userLogger.error(e);
            response.sendRedirect("Controller?command=go_to_ad_page&adIdInfo=" + adId + "&message=message.delete.unsuccessfully");
        }

    }
}
