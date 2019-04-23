package org.softuni.befit.validation.implementations;

import org.softuni.befit.domain.models.service.UserServiceModel;
import org.softuni.befit.validation.UserValidationService;
import org.springframework.stereotype.Component;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}