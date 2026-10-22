package ma.fstt.service;

import java.util.List;

public interface IDao<T> {
    void create(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(int id) throws Exception;
    T findById(int id) throws Exception;
    List<T> findAll() throws Exception;
}