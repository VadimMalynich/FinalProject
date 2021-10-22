package by.training.finalproject.controller;


import by.training.finalproject.controller.command.Command;
import by.training.finalproject.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandProvider provider = new CommandProvider();

    private File uploadFilePath;

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Configure uploadFilePath.
        String uploadFilePathParam = getServletConfig().getInitParameter("uploadFilePath");
        if (uploadFilePathParam == null) {
            throw new ServletException("MyServlet 'uploadFilePath' is not configured.");
        }
        uploadFilePath = new File(uploadFilePathParam);
        if (!uploadFilePath.exists()) {
            throw new ServletException("MyServlet 'uploadFilePath' does not exist.");
        }
        if (!uploadFilePath.isDirectory()) {
            throw new ServletException("MyServlet 'uploadFilePath' is not a directory.");
        }
        if (!uploadFilePath.canWrite()) {
            throw new ServletException("MyServlet 'uploadFilePath' is not writeable.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        Command command;

        name = request.getParameter("command");
        command = provider.takeCommand(name);

        command.execute(request, response, uploadFilePath);
    }

}
