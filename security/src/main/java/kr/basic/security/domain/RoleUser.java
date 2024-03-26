package kr.basic.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleUser {
    ROLE_USER("user"),ROLE_ADMIN("admin"),ROLE_MANAGER("manager");
    private final String role;
}
