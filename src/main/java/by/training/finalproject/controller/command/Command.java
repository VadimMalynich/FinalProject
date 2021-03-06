package by.training.finalproject.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException;
}
