package io.christianj98.securecapita.dtomapper;

import io.christianj98.securecapita.domain.User;
import io.christianj98.securecapita.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(user, user);
        return user;
    }
}
