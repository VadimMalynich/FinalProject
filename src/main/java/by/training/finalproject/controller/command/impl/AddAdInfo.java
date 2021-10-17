package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.*;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.AdInfoService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class AddAdInfo implements Command {
    private static final Logger userLogger = LogManager.getLogger(AddAdInfo.class);
    private static final String SAVE_DIR = "resources/images/adsPhoto";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Integer addType = Integer.valueOf(request.getParameter("addType"));
        String addTopic = request.getParameter("addTopic");
        String addMaterial = request.getParameter("addMaterial");
        Integer addSize = Integer.valueOf(request.getParameter("addSize"));
        Integer addSex = Integer.valueOf(request.getParameter("addSex"));
        String addDescription = request.getParameter("addDescription");
        String addPicture = request.getParameter("addPicture");
        ClothesSize size = ClothesSize.getByCode(addSize);
        ClothesSex sex = ClothesSex.getByCode(addSex);

        Object fileObject = request.getAttribute("addPicture");
        Ad ad = null;

        if (fileObject == null || fileObject instanceof FileUploadException) {
            ad = new Ad(addTopic, addMaterial, size, sex, addDescription);
        } else {
            FileItem fileItem = (FileItem) fileObject;

            // Get file name from uploaded file and trim path from it.
            // Some browsers (e.g. IE, Opera) also sends the path, which is completely irrelevant.
            String fileName = FilenameUtils.getName(fileItem.getName());
            String appPath = request.getServletContext().getRealPath(SAVE_DIR);

            // Prepare filename prefix and suffix for an unique filename in upload folder.
            String prefix = FilenameUtils.getBaseName(fileName) + "_";
            String suffix = "." + FilenameUtils.getExtension(fileName);
            try {
                // Prepare unique local file based on file name of uploaded file.
                File file = File.createTempFile(prefix, suffix, new File(appPath));

                // Write uploaded file to local file.
                fileItem.write(file);
                ad = new Ad(addTopic, addMaterial, size, sex, addDescription, file.getName());
            } catch (Exception e) {
                // Can be thrown by uniqueFile() and FileItem#write().
                userLogger.error(e);
            }
        }


//        String appPath = request.getServletContext().getRealPath(SAVE_DIR);
//        File fileSaveDir = new File(appPath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }

//        Part part = request.getPart("addPicture");
//        String fileName = extractFileName(part);
//        // refines the fileName in case it is an absolute path
//        fileName = new File(fileName).getName();
//        appPath += File.separator + fileName;
//        if (!fileName.isEmpty()) {
//            part.write(appPath);
//        }


        AdInfo adInfo = new AdInfo(user, addType, ad);

        ServiceProvider provider = ServiceProvider.getInstance();
        AdInfoService adsInfoService = provider.getAdInfoService();

        try {
            adsInfoService.add(adInfo);
            response.sendRedirect("Controller?command=go_to_home_page&message=message.addAd.complete");
        } catch (ServiceException e) {
            userLogger.error(e);
            response.sendRedirect("Controller?command=go_to_add_ad_page&message=message.addAd.unsuccessfully");
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
