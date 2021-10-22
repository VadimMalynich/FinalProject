package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.AdInfoService;
import by.training.finalproject.service.ClothesTypeService;
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

public class GoToEditAddPage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToEditAddPage.class);
    private static final String EDIT_AD = "editAdIdInfo";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("page", "Controller?command=go_to_edit_ad_page");

        if (request.getParameter(EDIT_AD) != null) {
            session.setAttribute(EDIT_AD, Integer.valueOf(request.getParameter(EDIT_AD)));
        }

        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }
        Integer editAdIdInfo = (Integer) session.getAttribute(EDIT_AD);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClothesTypeService clothesTypeService = provider.getClothesTypeService();
        AdInfoService adInfoService = provider.getAdInfoService();

        try {
            List<ClothesType> clothesTypeList = clothesTypeService.getAll();
            AdInfo adInfo = adInfoService.getAdInfo(editAdIdInfo);
            session.setAttribute("editAd", adInfo);
            session.setAttribute("clothesTypeList", clothesTypeList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editAdPage.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            userLogger.error(e);
            response.sendRedirect("Controller?command=go_to_user_profile_page&message=message.error.server");
        }
    }
}
