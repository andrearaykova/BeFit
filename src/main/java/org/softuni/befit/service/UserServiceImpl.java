package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.User;
import org.softuni.befit.domain.models.service.UserRegisterServiceModel;
import org.softuni.befit.domain.models.service.UserServiceModel;
import org.softuni.befit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public UserServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel) throws IOException {
        this.roleService.seedRolesInDb();

        User user = this.modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(
                this.bCryptPasswordEncoder.encode(userRegisterServiceModel.getPassword())
        );

        if (!Objects.equals(userRegisterServiceModel.getImage().getOriginalFilename(), "")) {
            String url = this.cloudinaryService.uploadImage(userRegisterServiceModel.getImage());
            user.setImageUrl(url);
        }

        if (this.userRepository.count() == 0) {
            user.setAuthorities(this.roleService.findAllRoles());
        } else {
            user.setAuthorities(new LinkedHashSet<>());

            user.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password!");
        }

        user.setPassword(!"".equals(userServiceModel.getPassword()) ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        user.setEmail(userServiceModel.getEmail());

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public boolean setUserRole(String id, String role) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect id!"));

        user.getAuthorities().clear();

        switch (role) {
            case "ROLE_USER":
                user.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                break;
            case "ROLE_MODERATOR":
                user.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                user.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                break;
            case "ROLE_ADMIN":
                user.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                user.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                user.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
                break;
        }

        try {
            this.userRepository.saveAndFlush(this.modelMapper.map(user, User.class));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}