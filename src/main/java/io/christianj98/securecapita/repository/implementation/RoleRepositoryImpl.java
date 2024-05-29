package io.christianj98.securecapita.repository.implementation;

import io.christianj98.securecapita.domain.Role;
import io.christianj98.securecapita.exception.ApiException;
import io.christianj98.securecapita.repository.RoleRepository;
import io.christianj98.securecapita.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

import static io.christianj98.securecapita.enumeration.RoleType.ROLE_USER;
import static io.christianj98.securecapita.query.RoleQuery.INSERT_ROLE_TO_USER;
import static io.christianj98.securecapita.query.RoleQuery.SELECT_ROLE_BY_NAME_QUERY;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Role create(final Role data) {
        return null;
    }

    @Override
    public Collection list(final int page, final int pageSize) {
        return null;
    }

    @Override
    public Role get(final Long id) {
        return null;
    }

    @Override
    public Role update(final Role data) {
        return null;
    }

    @Override
    public Boolean delete(final Long id) {
        return null;
    }

    @Override
    public void addRoleToUser(final Long userId, final String roleName) {
        log.info("Adding role {} to use id: {}", roleName, userId);
        try {
            Role role = jdbcTemplate.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName", roleName),
                    new RoleRowMapper());
            jdbcTemplate.update(INSERT_ROLE_TO_USER, Map.of("userId", userId, "roleId",
                    requireNonNull(role).getId()));
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No role found by name: " + ROLE_USER.name());
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Role getRoleByUserId(final Long userId) {
        return null;
    }

    @Override
    public Role getRoleByUserEmail(final String email) {
        return null;
    }

    @Override
    public void updateUserRole(final Long userId, final String roleName) {

    }
}
