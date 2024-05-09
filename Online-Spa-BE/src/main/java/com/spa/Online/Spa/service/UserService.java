package com.spa.Online.Spa.service;

import com.spa.Online.Spa.exception.UserException;
import com.spa.Online.Spa.model.User;

import java.util.List;

public interface UserService {
    public User findUserProfileByJwt(String jwt) throws UserException;

    public User findUserByEmail(String email) throws Exception;

    public List<User> findAllUsers();


    void updatePassword(User user, String newPassword);

    void sendPasswordResetEmail(User user);
}
