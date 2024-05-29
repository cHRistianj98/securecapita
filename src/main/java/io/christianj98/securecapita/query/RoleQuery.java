package io.christianj98.securecapita.query;

public class RoleQuery {
    public static final String SELECT_ROLE_BY_NAME_QUERY = "INSERT INTO UserRoles (user_id, role_id) " +
            "VALUES (:user_id, :roleId)";
    public static final String INSERT_ROLE_TO_USER = "SELECT * FROM Roles WHERE name = :name";
}
