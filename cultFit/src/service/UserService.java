package service;

import dao.UserDao;
import exception.ClassNotFoundException;
import exception.UserNotFoundException;
import util.DaoInterfaceService;

public class UserService {
    private DaoInterfaceService daoInterfaceService = DaoInterfaceService.getInstance();
    private UserDao userDao = daoInterfaceService.getUserDao();
    private final ClassService classService = new ClassService();
    public void bookClass(String userID, String classID)
    {
        try {
            classService.handleClassBooking(userID,classID);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cancelClassBooking(String userID, String classId)
    {
        try {
            classService.handleClassCancellation(userID,classId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
