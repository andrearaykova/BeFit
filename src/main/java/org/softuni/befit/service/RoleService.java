package org.softuni.befit.service;

import org.softuni.befit.domain.entitites.Role;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

    Set<Role> findAllRoles();

    Role findByAuthority(String authority);
}