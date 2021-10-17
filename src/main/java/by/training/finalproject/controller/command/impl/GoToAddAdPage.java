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
import java.io.IOException;
import java.util.List;

public class GoToAddAdPage implements Command {
    private static final Logger userLogger = LogManager.getLogger(GoToAddAdPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }

        ServiceProvider provider = ServiceProvider.getInstance();
        ClothesTypeService clothesTypeService = provider.getClothesTypeService();
        try {
            List<ClothesType> clothesTypeList = clothesTypeService.getAll();
            if (clothesTypeList == null) {
                response.sendRedirect("Controller?command=go_to_home_page&message=message.error.clothesTypes");
            } else {
                session.setAttribute("clothesTypes", clothesTypeList);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addAdPage.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            userLogger.error(e);
            response.sendRedirect("Controller?command=go_to_home_page&message=message.error.server");
        }
    }
}
