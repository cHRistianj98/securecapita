package io.christianj98.securecapita.repository.implementation;

import io.christianj98.securecapita.domain.User;
import io.christianj98.securecapita.exception.ApiException;
import io.christianj98.securecapita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private static final String COUNT_USER_EMAIL_QUERY = "";
    private static final String INSERT_USER_QUERY = "";
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public User create(final User user) {
        // Check the email is unique
        if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use. Please use a different email and try again.");
        }
        // save new user
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(Objects.requireNonNull(holder.getKey()).longValue());
        } catch (EmptyResultDataAccessException exception) {

        } catch (Exception exception) {

        }
        // Add role to the user
        // Send verification URL
        // Save URL in verification table
        // Send email to user with verification URL
        // Return the newly created user
        // If any errors, throw exception with proper message
        return null;
    }

    private SqlParameterSource getSqlParameterSource(User user) {
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

    private Integer getEmailCount(final String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }
}
