package io.christianj98.securecapita.service;

import io.christianj98.securecapita.domain.User;
import io.christianj98.securecapita.dto.UserDto;

public interface UserService {
    UserDto createUser(User user);
}
