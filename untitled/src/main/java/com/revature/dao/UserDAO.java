package com.revature.dao;

import com.revature.models.Users;

public interface UserDAO {
    Users getByEmail(String email);

    Users createUser(String first, String last, String email, String password, Boolean isManager);
}
