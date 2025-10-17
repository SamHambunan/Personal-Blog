package com.sam.personalBlog.model.enums;

public enum Permission {
    BLOGS_WRITE("blogs:write"),
    BLOGS_READ("blogs:read"),
    BLOGS_UPDATE("blogs:update"),
    BLOGS_DELETE("blogs:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
    public String getPermission()
    {
        return permission;
    }




}
