package model;

import constant.ClassType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Class {
    private String classID;
    private ClassType classType;
    private LocalDateTime startTime;
    private List<User> registeredUsers;
    private List<User> waitingUsers;
    private int capacity;
    private int bookedSlots;

    public Class(String classType, LocalDateTime startTime, int capacity) {
        this.classType = ClassType.valueOf(classType);
        this.startTime = startTime;
        this.capacity = capacity;
        this.bookedSlots = 0;
        this.registeredUsers = new ArrayList<>();
        this.waitingUsers = new ArrayList<>();
        this.classID = UUID.randomUUID().toString();
    }

    public String getClassID() {
        return classID;
    }

    public ClassType getClassType() {
        return classType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public List<User> getWaitingUsers() {
        return waitingUsers;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBookedSlots() {
        return bookedSlots;
    }

    public void setBookedSlots(int bookedSlots) {
        this.bookedSlots = bookedSlots;
    }

    public void addBookedUsers(User user)
    {
        registeredUsers.add(user);
    }
    public void addWaitingUsers(User user)
    {
        waitingUsers.add(user);
    }
}
