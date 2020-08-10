package com.svetikov.maketest.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CommonService<T> {

    void save(T t);
    T findById(int id);
    List<T> findAll();


}
