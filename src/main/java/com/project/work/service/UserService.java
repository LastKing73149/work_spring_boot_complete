package com.project.work.service;


import com.project.work.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
}
