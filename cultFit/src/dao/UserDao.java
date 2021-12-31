package dao;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao implements BaseDao<User>{
    private Map<String,User> userMap = new HashMap<>();
    @Override
    public User create(User user) {
        userMap.putIfAbsent(user.getUserId(), user);
        System.out.println(String.format("User Created: %s\n",user.getUserId()));
        return user;
    }

    @Override
    public User update(User user) {
        userMap.put(user.getUserId(),user);
        return user;
    }

    @Override
    public User findByID(String id) {
        return userMap.get(id);
    }

    @Override
    public void delete(User user) {
        userMap.remove(user.getUserId());
    }
}
