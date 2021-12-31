package dao;

public interface BaseDao <T> {
    T create(T entity);
    T update(T entity);
    T findByID(String id);
    void delete(T entity);
}
