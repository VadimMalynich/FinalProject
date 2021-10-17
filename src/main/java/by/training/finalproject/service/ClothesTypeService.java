package by.training.finalproject.service;

import by.training.finalproject.bean.ClothesType;

import java.util.List;

public interface ClothesTypeService {
    /**
     * Method for getting all clothes category data from database
     *
     * @return {@code List<ClothesType>} with info that lies in database
     * @throws ServiceException when the error occurred on the dao layer
     */
    List<ClothesType> getAll() throws ServiceException;

    /**
     * Method for validating data before add clothes category in database
     *
     * @param clothesType new category that needed to add
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void add(ClothesType clothesType) throws ServiceException;

    /**
     * Method for deleting clothes category
     *
     * @param id id of clothes type
     * @throws ServiceException when the error occurred on the dao layer
     */
    void delete(Integer id) throws ServiceException;

    /**
     * Method for validating data before edit clothes category in database
     *
     * @param clothesType information about the clothes' category that will overwrite the data in the database
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void edit(ClothesType clothesType) throws ServiceException;

    /**
     * Method for getting name of clothes category
     *
     * @param id id of clothes category which name need to take
     * @return {@code String} name of clothes category
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    String getClothesCategory(Integer id) throws ServiceException;
}
