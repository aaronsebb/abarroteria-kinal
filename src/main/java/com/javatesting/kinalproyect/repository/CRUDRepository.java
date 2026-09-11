package main.java.com.javatesting.kinalproyect.repository;

import javafx.collections.ObservableList;

public interface CRUDRepository<T> {
    
    ObservableList<T> findAll();
    
    boolean save(T entidad);
    
    boolean deleteById(String id);
    
    boolean updateById(T entidad);
}