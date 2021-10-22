package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.controller.command.Command;
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

public class GoToTypesPage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToTypesPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("page", "Controller?command=go_to_types_page");
        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }

        ServiceProvider provider = ServiceProvider.getInstance();
        ClothesTypeService clothesTypeService = provider.getClothesTypeService();

        try {
            List<ClothesType> clothesTypeList = clothesTypeService.getAll();
            session.setAttribute("clothesTypeList", clothesTypeList);
        } catch (ServiceException e) {
            userLogger.error(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/clothesTypesPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
