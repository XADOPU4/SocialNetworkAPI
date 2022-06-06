package com.xadopu4shelepushka.socialnetwork.repository.implementations;

import com.xadopu4shelepushka.socialnetwork.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadopu4shelepushka.socialnetwork.repository.UserRepository;
import org.postgresql.Driver;

public class UserRepositoryImpl implements UserRepository {
    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/SocialNetwork";
    private static final String USER = "postgres";
    private static final String PASSWORD = "pASSw0rd";

    public UserRepositoryImpl(){
        //Регистрация драйвера JDBC для PostgreSQL
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            System.out.println("No registered driver found");
        }
    }

    //Метод для извлечения всех пользователей со списком их друзей
    @Override
    public List<User> getAll() {
        List<User> resultList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            if(connection!= null) {
                var resultSetUserList = connection.createStatement().executeQuery("SELECT * FROM \"User\"");
                while (resultSetUserList.next()) {

                    User userToAdd = new User(
                            resultSetUserList.getInt("id"),
                            resultSetUserList.getString("name"),
                            resultSetUserList.getString("login"),
                            resultSetUserList.getString("password"),
                            resultSetUserList.getString("avatar_path"),
                            null,
                            resultSetUserList.getString("description")
                    );

                    var resultSetUserFriendsList = connection.createStatement().executeQuery("SELECT * FROM \"User\" " +
                            "WHERE \"User\".\"id\" " +
                            "IN (SELECT second_user_id FROM \"Friends\" WHERE first_user_id = " + userToAdd.getId() + ")");

                    List<User> friendList = new ArrayList<>();

                    while(resultSetUserFriendsList.next()){
                        User friendToAdd = new User(
                                resultSetUserFriendsList.getInt("id"),
                                resultSetUserFriendsList.getString("name"),
                                resultSetUserFriendsList.getString("login"),
                                resultSetUserFriendsList.getString("password"),
                                resultSetUserFriendsList.getString("avatar_path"),
                                null,
                                resultSetUserFriendsList.getString("description")
                        );

                        friendList.add(friendToAdd);
                    }
                    userToAdd.setFriends(friendList);

                    resultList.add(userToAdd);
                }
                return resultList;
            }
        } catch (SQLException e) {
            System.out.println("Something failed in getAll()");
        }
        return null;
    }

    @Override
    public User getById(int id) {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            if(connection!= null){
                var resultSet = connection.createStatement().executeQuery("SELECT * FROM \"User\" WHERE \"User\".\"id\" = " + id);

                if(resultSet.next()){
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("avatar_path"),
                            null,
                            resultSet.getString("description")
                    );

                    var resultSetUserFriendsList = connection.createStatement().executeQuery("SELECT * FROM \"User\" " +
                            "WHERE \"User\".\"id\" " +
                            "IN (SELECT second_user_id FROM \"Friends\" WHERE first_user_id = " + user.getId() + ")");

                    List<User> friendList = new ArrayList<>();

                    while(resultSetUserFriendsList.next()){
                        User friendToAdd = new User(
                                resultSetUserFriendsList.getInt("id"),
                                resultSetUserFriendsList.getString("name"),
                                resultSetUserFriendsList.getString("login"),
                                resultSetUserFriendsList.getString("password"),
                                resultSetUserFriendsList.getString("avatar_path"),
                                null,
                                resultSetUserFriendsList.getString("description")
                        );

                        friendList.add(friendToAdd);
                    }
                    user.setFriends(friendList);
                }
            }
        } catch (SQLException e) {
            System.out.println("Something failed in getById(int)");
        }
        return user;
    }

    @Override
    public boolean create(User value) {

        var resultInsert = 0;

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            if(connection!= null){
                resultInsert = connection.createStatement().executeUpdate(String.format(
                        "INSERT INTO \"User\"(name, login, password, avatar_path, description) VALUES ('%s', '%s', '%s','%s', '%s')",
                        value.getUserName(),
                        value.getLogin(),
                        value.getPassword(),
                        value.getAvatarPath(),
                        value.getDescription()
                ));
            }
        } catch (SQLException e) {
            System.out.println("Something failed in create()");
        }
        return resultInsert > 0;
    }

    @Override
    public boolean update(User value) {
        var resultUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            if(connection!= null){
                resultUpdate = connection.createStatement().executeUpdate(String.format(
                        "UPDATE \"User\" SET \"name\"='%s',\n" +
                                "\"login\"='%s',\n" +
                                "\"password\"='%s',\n" +
                                "\"avatar_path\"='%s',\n" +
                                "\"description\"='%s'\n" +
                                "WHERE \"id\"=%d;",
                                value.getUserName(),
                                value.getLogin(),
                                value.getPassword(),
                                value.getAvatarPath(),
                                value.getDescription(),
                                value.getId()
                ));
            }
        } catch (SQLException e) {
            System.out.println("Something failed in update(User value)");
        }
        return resultUpdate > 0;
    }

    @Override
    public boolean delete(User value) {
        var resultDelete = 0;
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            if(connection!= null){
                resultDelete = connection.createStatement().executeUpdate(String.format(
                        "INSERT INTO \"User\" VALUES ('%s', '%s', '%s', '%s', '%s')",
                        value.getUserName(),
                        value.getLogin(),
                        value.getPassword(),
                        value.getAvatarPath(),
                        value.getDescription()
                ));
            }
        } catch (SQLException e) {
            System.out.println("Something failed in delete(User value)");
        }
        return resultDelete > 0;
    }
}
