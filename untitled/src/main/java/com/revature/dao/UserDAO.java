package com.revature.dao;

import com.revature.models.Users;

import java.util.List;

public interface UserDAO {
    Users getByEmail(String email);

    Users createUser(String first, String last, String email, String password, Boolean isManager);

    List<Users> getAllUsers();

    boolean checkLogin (String email, String password);
//    Users updateuser(Users user, int isUpdated);
}
