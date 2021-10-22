package by.training.finalproject.controller.command.impl;

import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.ClothesTypeService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteClothesType implements Command {
    private static final Logger userLogger = LogManager.getLogger(DeleteClothesType.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("deleteTypeId"));

        ServiceProvider provider = ServiceProvider.getInstance();
        ClothesTypeService clothesTypeService = provider.getClothesTypeService();

        try {
            clothesTypeService.delete(id);
            response.sendRedirect("Controller?command=go_to_types_page&message=message.deleteType.complete");
        } catch (ServiceException e) {
            userLogger.info(e);
            response.sendRedirect("Controller?command=go_to_types_page&message=message.deleteType.unsuccessfully");
        }
    }
}
