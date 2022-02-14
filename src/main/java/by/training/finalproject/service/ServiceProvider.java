package by.training.finalproject.service;

import by.training.finalproject.service.jdbc.AdInfoServiceImpl;
import by.training.finalproject.service.jdbc.ClothesTypeServiceImpl;
import by.training.finalproject.service.jdbc.CommentServiceImpl;
import by.training.finalproject.service.jdbc.UserServiceImpl;

public final class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private final UserService userService = new UserServiceImpl();
    private final AdInfoService adInfoService = new AdInfoServiceImpl();
    private final ClothesTypeService clothesTypeService = new ClothesTypeServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public AdInfoService getAdInfoService() {
        return adInfoService;
    }

    public ClothesTypeService getClothesTypeService() {
        return clothesTypeService;
    }

    public CommentService getCommentService() {
        return commentService;
    }
}