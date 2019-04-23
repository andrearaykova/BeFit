package org.softuni.befit.service;

import org.softuni.befit.domain.models.service.UserRegisterServiceModel;
import org.softuni.befit.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel) throws IOException;

    UserServiceModel findUserByUserName(String username);

    UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);

    List<UserServiceModel> findAllUsers();

    boolean setUserRole(String id, String role);
}