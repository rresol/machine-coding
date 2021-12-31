import model.Class;
import model.User;
import service.ClassService;
import service.UserService;
import util.DaoInterfaceService;

import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args)
    {
        DaoInterfaceService daoInterfaceService = DaoInterfaceService.getInstance();
        User user1 = new User("Test-User-1");
        User user2 = new User("Test-User-2");
        User user3 = new User("Test-User-3");

        Class class1 = new Class("YOGA", LocalDateTime.now(),2);
        Class class2 = new Class("DANCE", LocalDateTime.now(),10);
        Class class3 = new Class("GYM", LocalDateTime.now(),10);

        daoInterfaceService.getUserDao().create(user1);
        daoInterfaceService.getUserDao().create(user2);
        daoInterfaceService.getUserDao().create(user3);

        daoInterfaceService.getClassDao().create(class1);
        daoInterfaceService.getClassDao().create(class2);
        daoInterfaceService.getClassDao().create(class3);

        final UserService userService = new UserService();
        final ClassService classService = new ClassService();

        userService.bookClass(user1.getUserId(),class1.getClassID());
        userService.bookClass(user2.getUserId(),class1.getClassID());
        userService.bookClass(user2.getUserId(),class3.getClassID());
        userService.bookClass(user2.getUserId(),class2.getClassID());
        userService.bookClass(user3.getUserId(),class1.getClassID());

        userService.cancelClassBooking(user2.getUserId(),class1.getClassID());
    }
}
