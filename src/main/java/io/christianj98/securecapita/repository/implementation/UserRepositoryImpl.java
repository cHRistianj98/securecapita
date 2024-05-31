package io.christianj98.securecapita.repository.implementation;

import io.christianj98.securecapita.domain.Role;
import io.christianj98.securecapita.domain.User;
import io.christianj98.securecapita.exception.ApiException;
import io.christianj98.securecapita.repository.RoleRepository;
import io.christianj98.securecapita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static io.christianj98.securecapita.enumeration.RoleType.ROLE_USER;
import static io.christianj98.securecapita.enumeration.VerificationType.ACCOUNT;
import static io.christianj98.securecapita.query.UserQuery.COUNT_USER_EMAIL_QUERY;
import static io.christianj98.securecapita.query.UserQuery.INSERT_ACCOUNT_VERIFICATION_URL_QUERY;
import static io.christianj98.securecapita.query.UserQuery.INSERT_USER_QUERY;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create(final User user) {
        if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use. Please use a different email and try again.");
        }
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY,
                    Map.of("userId", user.getId(), "url", verificationUrl));
            // emailService.sendVerificationUrl(user.getFirstName(), user.getEmail(), verificationUrl, ACCOUNT);
            user.setEnabled(false);
            user.setNotLocked(true);
            return user;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred: Please try again.");
        }
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
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

    private String getVerificationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/verify" + type + "/" + key)
                .toUriString();
    }
}