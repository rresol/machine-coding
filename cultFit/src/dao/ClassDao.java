package dao;

import model.Class;

import java.util.HashMap;
import java.util.Map;

public class ClassDao implements BaseDao<Class>{
    private Map<String,Class> classMap = new HashMap<>();
    @Override
    public Class create(Class clazz) {
        classMap.putIfAbsent(clazz.getClassID(),clazz);
        System.out.println(String.format("Class Created: %s\n",clazz.getClassID()));
        return clazz;
    }

    @Override
    public Class update(Class clazz) {
        classMap.putIfAbsent(clazz.getClassID(),clazz);
        return clazz;
    }

    @Override
    public Class findByID(String id) {
        return classMap.get(id);
    }

    @Override
    public void delete(Class clazz) {
        classMap.remove(clazz);
    }
}
