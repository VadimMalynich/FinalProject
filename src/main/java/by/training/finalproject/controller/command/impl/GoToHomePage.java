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

public class GoToHomePage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToHomePage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String temp = (String) session.getAttribute("locale");
        session.setAttribute("page", "Controller?command=go_to_home_page");
        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }
        if (request.getParameter("locale") != null) {
            session.setAttribute("locale", request.getParameter("locale"));
        }
        ServiceProvider provider = ServiceProvider.getInstance();
        AdInfoService adInfoService = provider.getAdInfoService();
        ClothesTypeService clothesTypeService = provider.getClothesTypeService();

        try {
            List<AdInfo> adInfoList = adInfoService.getAll();
            List<ClothesType> clothesTypeList = clothesTypeService.getAll();
            session.setAttribute("adsList", adInfoList);
            session.setAttribute("categoryCountList", clothesTypeList);
            session.removeAttribute("searchAdsList");
            session.removeAttribute("searchAd");
            session.removeAttribute("filterClothesType");
            session.removeAttribute("filterAdsList");
        } catch (ServiceException e) {
            userLogger.error(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        requestDispatcher.forward(request, response);
    }
}
