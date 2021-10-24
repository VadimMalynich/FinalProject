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

public class FilterAdsByType implements Command {
    private static final Logger userLogger = LogManager.getLogger(FilterAdsByType.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer typeId = Integer.valueOf(request.getParameter("filterIdType"));
        session.setAttribute("page", "Controller?command=filter_ads&filterIdType=" + typeId);
        ServiceProvider provider = ServiceProvider.getInstance();
        AdInfoService adInfoService = provider.getAdInfoService();
        ClothesTypeService clothesTypeService = provider.getClothesTypeService();


        try {
            List<AdInfo> filterAdsList = adInfoService.filterByType(typeId);
            List<ClothesType> clothesTypeList = clothesTypeService.getAll();
            String category = clothesTypeService.getClothesCategory(typeId);
            session.setAttribute("filterAdsList", filterAdsList);
            session.setAttribute("categoryCountList", clothesTypeList);
            session.setAttribute("filterClothesType", category);
            session.removeAttribute("adsList");
            session.removeAttribute("searchAdsList");
            session.removeAttribute("searchAd");
        } catch (ServiceException e) {
            userLogger.error(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        requestDispatcher.forward(request, response);
    }
}
