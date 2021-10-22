package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.*;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.AdInfoService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditAdInfo implements Command {
    private static final Logger userLogger = LogManager.getLogger(EditAdInfo.class);
    private static final String SAVE_DIR = "resources/images/adsPhoto";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, File uploadFilePath) throws ServletException, IOException {
        HttpSession session = request.getSession();

        AdInfo previousAd = (AdInfo) session.getAttribute("editAd");

        Integer editAdIdInfo = (Integer) session.getAttribute("editAdIdInfo");
        Integer editType = Integer.valueOf(request.getParameter("editType"));
        String editTopic = request.getParameter("editTopic");
        String editMaterial = request.getParameter("editMaterial");
        Integer editSize = Integer.valueOf(request.getParameter("editSize"));
        Integer editSex = Integer.valueOf(request.getParameter("editSex"));
        String editDescription = request.getParameter("editDescription");
        ClothesSize size = ClothesSize.getByCode(editSize);
        ClothesSex sex = ClothesSex.getByCode(editSex);

        User user = (User) session.getAttribute("user");
        Object fileObject = request.getAttribute("editPicture");
        Ad ad = null;


        if (fileObject == null || fileObject instanceof FileUploadException) {
            ad = new Ad(editTopic, editMaterial, size, sex, editDescription, previousAd.getAd().getPicture());
        } else {
            FileItem fileItem = (FileItem) fileObject;
            String uploadAppPath = request.getServletContext().getRealPath(SAVE_DIR);

            // Get file name from uploaded file and trim path from it.
            // Some browsers (e.g. IE, Opera) also sends the path, which is completely irrelevant.
            String fileName = FilenameUtils.getName(fileItem.getName());

            // Prepare filename prefix and suffix for an unique filename in upload folder.
            String prefix = FilenameUtils.getBaseName(fileName) + "_";
            String suffix = "." + FilenameUtils.getExtension(fileName);
            try {
                // Prepare unique local file based on file name of uploaded file.
                File file = File.createTempFile(prefix, suffix, uploadFilePath);
                Files.delete(Paths.get(file.getPath()));

                // Write uploaded file to local file.
                fileItem.write(file);
                Files.copy(file.toPath(), new File(uploadAppPath + File.separator + file.getName()).toPath());
                ad = new Ad(editTopic, editMaterial, size, sex, editDescription, file.getName());
            } catch (Exception e) {
                // Can be thrown by uniqueFile() and FileItem#write().
                userLogger.error(e);
            }
        }

        ServiceProvider provider = ServiceProvider.getInstance();
        AdInfoService adsInfoService = provider.getAdInfoService();

        AdInfo adInfo = new AdInfo(editAdIdInfo, previousAd.getUserInfo(), editType, ad);

        try {
            adsInfoService.edit(adInfo);
            session.removeAttribute("editAd");
            session.removeAttribute("editAdIdInfo");
            if (user.getRole().getValue() == 0) {
                response.sendRedirect("Controller?command=go_to_home_page&message=message.editAd.complete");
            } else {
                response.sendRedirect("Controller?command=go_to_user_profile_page&message=message.editAd.complete");
            }
        } catch (ServiceException e) {
            userLogger.info(e);
            if (user.getRole().getValue() == 0) {
                response.sendRedirect("Controller?command=go_to_home_page&message=message.edit.unsuccessfully");
            } else {
                response.sendRedirect("Controller?command=go_to_edit_ad_page&message=message.edit.unsuccessfully");
            }
        }
    }
}
