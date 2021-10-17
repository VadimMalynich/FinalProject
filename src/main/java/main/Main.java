package main;


import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.Comments;
import by.training.finalproject.controller.command.impl.LikeAd;
import by.training.finalproject.service.*;
import by.training.finalproject.service.impl.AdInfoServiceImpl;
import by.training.finalproject.service.impl.ClothesTypeServiceImpl;
import by.training.finalproject.service.validation.PBKDF2Hasher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final Logger userLogger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ServiceProvider provider = ServiceProvider.getInstance();
        CommentService commentService = provider.getCommentService();
        try {
            commentService.add(new Comments(1, 1, " ewdwdw dqwdsa",
                    new Timestamp(Calendar.getInstance().getTimeInMillis())));
        } catch (ServiceException e) {
            userLogger.info(e);
        }


    }
}
