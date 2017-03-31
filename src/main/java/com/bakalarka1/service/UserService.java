package com.bakalarka1.service;

import com.bakalarka1.model.User;

/**
 * Created by Martin on 30.03.2017.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}