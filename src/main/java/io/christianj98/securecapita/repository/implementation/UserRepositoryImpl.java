package io.christianj98.securecapita.repository.implementation;

import io.christianj98.securecapita.domain.User;
import io.christianj98.securecapita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public User create(final User data) {
        // Check the email is unique
        return null;
    }

    @Override
    public Collection<User> list(final int page, final int pageSize) {
        return null;
    }

    @Override
    public User get(final Long id) {
        return null;
    }

    @Override
    public User update(final User data) {
        return null;
    }

    @Override
    public Boolean delete(final Long id) {
        return null;
    }
}
