package com.xadopu4shelepushka.socialnetwork.repository;

import com.xadopu4shelepushka.socialnetwork.models.User;

import java.util.List;

/*
Интерфейс репозитория,
оперирующий только пользователями
*/
public interface UserRepository {

    List<User> getAll();
    User getById(int id);
    boolean create(User value);
    boolean update(User value);
    boolean delete(User value);
}
