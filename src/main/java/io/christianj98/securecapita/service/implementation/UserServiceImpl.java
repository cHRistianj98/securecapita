package io.christianj98.securecapita.service.implementation;

import io.christianj98.securecapita.domain.User;
import io.christianj98.securecapita.dto.UserDto;
import io.christianj98.securecapita.dtomapper.UserDtoMapper;
import io.christianj98.securecapita.repository.UserRepository;
import io.christianj98.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
    public UserDto createUser(final User user) {
        return UserDtoMapper.fromUser(userRepository.create(user));
    }
}
