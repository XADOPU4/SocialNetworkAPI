package com.xadopu4shelepushka.socialnetwork.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Validatable {
    private int id;
    @NonNull private String userName;
    @NonNull private String login;
    @NonNull private String password;
    private String avatarPath;
    private List<User> friends;
    private String description;


    @JsonIgnore
    @Override
    public boolean isValid() {
        return !userName.isEmpty() && !login.isEmpty() && !password.isEmpty();
    }


}
