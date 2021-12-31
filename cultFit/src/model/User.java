package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private List<Class> bookedClasses;

    public User(String name) {
        this.name = name;
        this.bookedClasses = new ArrayList<>();
        this.userId = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public List<Class> getBookedClasses() {
        return bookedClasses;
    }

    public String getUserId() {
        return userId;
    }
    public void updateBookedClasses(Class clazz)
    {
        bookedClasses.add(clazz);
    }
}
