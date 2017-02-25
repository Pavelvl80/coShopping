package com.dao;

import com.model.BaseEntity;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
public interface AbstractDAO<T extends BaseEntity> {
    T save(T t);

    T getById(T t);
}
