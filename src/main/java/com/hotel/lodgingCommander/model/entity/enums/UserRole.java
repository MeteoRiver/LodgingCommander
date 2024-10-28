package com.hotel.lodgingCommander.model.entity.enums;

import com.hotel.lodgingCommander.enums.CodeEnum;

public enum UserRole implements CodeEnum<String> {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

    private final String code;

    UserRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
