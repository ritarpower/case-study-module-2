package service;

import java.util.List;

public interface ITokenService <T>{
    List<T> getAll();
    void add(T t);
    T findByCode(String code);
    void update(T t);
    void delete(T t);
}
