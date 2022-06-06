package com.xadopu4shelepushka.socialnetwork.API;

import com.xadopu4shelepushka.socialnetwork.models.User;
import com.xadopu4shelepushka.socialnetwork.repository.UserRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xadopu4shelepushka.socialnetwork.repository.implementations.UserRepositoryImpl;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {
    //Строка под ответы
    private String message;
    //Ссылка под объект какого-то репозитория (см. метод init() )
    private UserRepository repository;
    //Объект для записи других объектов в JSON
    private ObjectMapper mapper;

    //Этот метод вызывается единожды при создании этого сервлета
    public void init() {
        message = "";
//        repository = TestUserRepository.getInstance();
        repository = new UserRepositoryImpl();
        mapper = new ObjectMapper();
    }




    //Этот метод срабатывает, если приходит запрос GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idParameter = request.getParameter("id");

        if (idParameter != null) {
            int idToFind = Integer.parseInt(idParameter);
            User user = repository.getById(idToFind);

            message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        }
        else{
            List<User> users = repository.getAll();

            message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(message);

    }

    //Этот метод срабатывает, если приходит запрос POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userJson = request.getReader().lines().collect(Collectors.joining(""));

        User userToAdd = mapper.readValue(userJson, User.class);

        if(repository.create(userToAdd)){
            response.setStatus(201);
        }
        else{
            response.setStatus(400);
        }

        response.setContentType("application/json");


        String responseText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userToAdd);

        PrintWriter out = response.getWriter();
        out.print(responseText);


    }

    //Этот метод срабатывает, если приходит запрос PUT
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userJson = request.getReader().lines().collect(Collectors.joining(""));

        User userToUpdate = mapper.readValue(userJson, User.class);

        if(repository.update(userToUpdate)){
            response.setStatus(200);
        }
        else{
            response.setStatus(400);
        }

        response.setContentType("application/json");


        String responseText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userToUpdate);

        PrintWriter out = response.getWriter();
        out.print(responseText);
    }


    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userJson = request.getReader().lines().collect(Collectors.joining(""));

        User userToDelete = mapper.readValue(userJson, User.class);

        if(repository.delete(userToDelete)){
            response.setStatus(204);
        }
        else{
            response.setStatus(400);
        }

        response.setContentType("application/json");


        String responseText = mapper.writeValueAsString(userToDelete);

        PrintWriter out = response.getWriter();
        out.print(responseText);
    }

    public void destroy() {
    }
}