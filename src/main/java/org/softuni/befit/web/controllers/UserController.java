package org.softuni.befit.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.Role;
import org.softuni.befit.domain.models.binding.UserEditBindingModel;
import org.softuni.befit.domain.models.binding.UserRegisterBindingModel;
import org.softuni.befit.domain.models.service.RoleServiceModel;
import org.softuni.befit.domain.models.service.UserRegisterServiceModel;
import org.softuni.befit.domain.models.service.UserServiceModel;
import org.softuni.befit.domain.models.view.UserAllViewModel;
import org.softuni.befit.domain.models.view.UserProfileViewModel;
import org.softuni.befit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register() {
        return super.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute @Valid UserRegisterBindingModel model,
                                        Errors errors) throws IOException {
        if (!model.getPassword().equals(model.getConfirmPassword()) ||
                errors.hasErrors()) {
            return super.view("register");
        }

        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(model, UserRegisterServiceModel.class);
        this.userService.registerUser(userRegisterServiceModel);

        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("login");
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView) {
        modelAndView
                .addObject("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));

        return super.view("profile", modelAndView);
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
        modelAndView
                .addObject("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));

        return super.view("edit-profile", modelAndView);
    }

    @PatchMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@ModelAttribute UserEditBindingModel model) {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("edit-profile");
        }

        this.userService.editUserProfile(this.modelMapper.map(model, UserServiceModel.class), model.getOldPassword());

        return super.redirect("/users/profile");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = this.modelMapper.map(u, UserAllViewModel.class);
                    user.setAuthorities(u.getAuthorities().stream().map(RoleServiceModel::getAuthority).collect(Collectors.toSet()));

                    return user;
                })
                .collect(Collectors.toList());

        modelAndView.addObject("users", users);

        return super.view("all-users", modelAndView);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView showAdminPage(ModelAndView modelAndView){
        List<UserServiceModel> userServiceModels = this.userService.findAllUsers();

        List<UserAllViewModel> users =
                userServiceModels
                        .stream()
                        .map(u -> {
                            UserAllViewModel v = this.modelMapper.map(u, UserAllViewModel.class);
                            Set<String> authorities = u.getAuthorities()
                                    .stream()
                                    .map(RoleServiceModel::getAuthority)
                                    .collect(Collectors.toSet());

                            v.setAuthorities(authorities);
                            return v;
                        })
                        .collect(Collectors.toList());

        modelAndView.addObject("model", users);

        return view("admin", modelAndView);
    }

    @GetMapping("/setAuth/{role}/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setRole(
            @PathVariable("role") String role,
            @PathVariable("id") String id
    ){
        if(!this.userService.setUserRole(id,role)){
            return redirect("/users/admin?error=true");
        }

        return redirect("/users/admin");
    }
}