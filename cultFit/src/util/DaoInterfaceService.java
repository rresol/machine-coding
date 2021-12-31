package util;

import dao.ClassDao;
import dao.UserDao;

public class DaoInterfaceService {
    private static DaoInterfaceService daoInterfaceService;
    private final UserDao userDao;
    private final ClassDao classDao;

    private DaoInterfaceService() {
        this.userDao = new UserDao();
        this.classDao = new ClassDao();
    }

    public static DaoInterfaceService getInstance() {
        if (daoInterfaceService == null) {
            synchronized (DaoInterfaceService.class) {
                daoInterfaceService = new DaoInterfaceService();
            }
        }
        return daoInterfaceService;
    }

    public static DaoInterfaceService getDaoInterfaceService() {
        return daoInterfaceService;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ClassDao getClassDao() {
        return classDao;
    }
}
