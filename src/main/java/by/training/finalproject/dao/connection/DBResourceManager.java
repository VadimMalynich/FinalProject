package by.training.finalproject.dao.connection;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();

    private final ResourceBundle bundle = ResourceBundle.getBundle("db.database");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
