package repository;

import java.util.List;

public interface TokenRepository <T>{
    List<T> getAll();
    void add(T t);
    T findByNumber(int number);
    void update(T t);
    void delete(T t);

}
