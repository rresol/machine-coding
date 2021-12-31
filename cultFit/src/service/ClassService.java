package service;

import dao.ClassDao;
import dao.UserDao;
import exception.ClassNotFoundException;
import exception.UserNotFoundException;
import model.Class;
import model.User;
import util.DaoInterfaceService;

import java.time.LocalDateTime;
import java.util.List;

public class ClassService {
    private static final DaoInterfaceService daoInterfaceService = DaoInterfaceService.getInstance();
    private ClassDao  classDao = daoInterfaceService.getClassDao();
    private UserDao userDao = daoInterfaceService.getUserDao();
    private void validateUser(String userID) throws UserNotFoundException {
        User user = userDao.findByID(userID);
        if(user==null)
            throw new UserNotFoundException("User Not Found");
    }
    private void validateClass(String classID) throws ClassNotFoundException {
        Class clazz = classDao.findByID(classID);
        if(clazz==null)
            throw new ClassNotFoundException("Class Not Found");
    }
    private void validateIfUserRegisteredInTheClass(String userID, String classId) throws IllegalArgumentException {
        final User user = userDao.findByID(userID);
        final Class clazz = classDao.findByID(classId);
        final List<User> registeredUserList = clazz.getRegisteredUsers();
        if(!registeredUserList.contains(user))
            throw new IllegalArgumentException("User Not Registered in the class");
    }
    private void validateIfUserEligibleForCancelation(String userID, String classId) throws IllegalArgumentException {
        final User user = userDao.findByID(userID);
        final Class clazz = classDao.findByID(classId);
        final LocalDateTime clazzStartTime = clazz.getStartTime();

    }



    public void handleClassBooking(String userID, String classID) throws UserNotFoundException, ClassNotFoundException {
        validateUser(userID);
        validateClass(classID);
        final User user = userDao.findByID(userID);
        final Class clazz = classDao.findByID(classID);
        if(clazz.getBookedSlots()<clazz.getCapacity()) {
            clazz.addBookedUsers(user);
            clazz.setBookedSlots(clazz.getBookedSlots()+1);
            user.updateBookedClasses(clazz);
            classDao.update(clazz);
            userDao.update(user);
        }
        else
            clazz.addWaitingUsers(user);
    }

    public void handleClassCancellation(String userId, String classID) throws UserNotFoundException, ClassNotFoundException, IllegalArgumentException {
        validateUser(userId);
        validateClass(classID);
        validateIfUserRegisteredInTheClass(userId, classID);
        validateIfUserEligibleForCancelation(userId,classID);
        final User user = userDao.findByID(userId);
        final Class clazz = classDao.findByID(classID);
        final List<User> registeredUser = clazz.getRegisteredUsers();
        final List<Class> bookedClasses = user.getBookedClasses();
        registeredUser.remove(user);
        clazz.setBookedSlots(clazz.getBookedSlots()-1);
        bookedClasses.remove(clazz);
        userDao.update(user);
        classDao.update(clazz);
        final List<User> waitingUsers = clazz.getWaitingUsers();
        final User userAtFront = waitingUsers.size() > 0 ? waitingUsers.get(0): null;
        if(userAtFront!=null)
        {
            handleClassBooking(userAtFront.getUserId(),clazz.getClassID());
        }
    }
}
