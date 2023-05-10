package com.paradiseco.bubblepop.model;

import com.paradiseco.bubblepop.entity.Role;
import com.paradiseco.bubblepop.entity.UserEntity;

public class UserModel {
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private Role role;

    public static UserModel toModel(UserEntity entity){
        UserModel model = new UserModel();
        model.setUsername(entity.getUsername());
        model.setRole(entity.getRole());
        model.setPassword(entity.getPassword());
        return model;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
