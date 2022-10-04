package com.revature.dao;

import com.revature.models.Users;

public interface UserDAO {
    Users getByEmail(String email);
}
