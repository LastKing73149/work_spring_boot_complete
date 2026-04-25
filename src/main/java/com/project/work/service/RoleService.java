package com.project.work.service;

import com.project.work.model.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);
    List<Role> getAllRoles();
    Role findByName(String name);
}
