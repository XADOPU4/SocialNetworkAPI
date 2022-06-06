package com.xadopu4shelepushka.socialnetwork.repository.implementations;

import com.xadopu4shelepushka.socialnetwork.models.User;
import com.xadopu4shelepushka.socialnetwork.repository.UserRepository;

import java.util.*;

/*
* Тестовый репозиторий с данными в списках
* */
public class TestUserRepositoryImpl implements UserRepository {

    private Set<User> users;
    private static UserRepository instance = null;

    //Синглтон для всего приложения
    public static UserRepository getInstance() {
        if(instance == null){
            instance = new TestUserRepositoryImpl();
        }
        return instance;
    }

    //Конструктор для синглтона
    private TestUserRepositoryImpl(){
        init();
    }

    //Добавление сущностей в список
    private void init(){
        users = new HashSet<>(List.of(
                new User(0,
                        "Boy",
                        "boyLogin",
                        "boyPassword",
                        null,
                        new ArrayList<>(List.of(
                                new User(1,
                                        "Next",
                                        "nextLogin",
                                        "nextPassword",
                                        null,
                                        null,
                                        null)
                        )),
                        null),
                new User(1,
                        "Next",
                        "nextLogin",
                        "nextPassword",
                        null,
                        new ArrayList<>(List.of(
                                new User(0,
                                        "Boy",
                                        "boyLogin",
                                        "boyPassword",
                                        null,
                                        null,
                                        null)
                        )),
                        null)));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User getById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @Override
    public boolean create(User value) {
        if(!value.isValid()) return false;

        return users.add(value);
    }

    @Override
    public boolean update(User value) {

        if(!value.isValid()) return false;

        try{
            User userToUpdate = users.stream().filter(user -> user.getId() == value.getId()).findFirst().get();
            users.remove(userToUpdate);
            users.add(value);

        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(User value) {
        return users.remove(value);
    }
}
