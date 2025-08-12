package management;

import java.util.List;

public interface IManagement<T> {
    void add(T t);
    void delete(long id) throws Exception;
    void update(long id, T t) throws Exception;
    T findById(long id) throws Exception;
    List<T> findAll();
    int findIndexById(long id) throws Exception;

}
