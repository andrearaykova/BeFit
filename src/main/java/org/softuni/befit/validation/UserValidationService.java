package org.softuni.befit.validation;

import org.softuni.befit.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}