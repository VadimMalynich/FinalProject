package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.ClothesTypeService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddClothesType implements Command {
    private static final Logger userLogger = LogManager.getLogger(AddClothesType.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        String category =request.getParameter("addCategory");

        ServiceProvider provider = ServiceProvider.getInstance();
        ClothesTypeService clothesTypeService = provider.getClothesTypeService();

        ClothesType clothesType = new ClothesType(category);

        try {
            clothesTypeService.add(clothesType);
            response.sendRedirect("Controller?command=go_to_types_page&message=message.addType.complete");
        } catch (ServiceException e) {
            userLogger.info(e);
            response.sendRedirect("Controller?command=go_to_types_page&message=message.add.unsuccessfully");
        }
    }
}
