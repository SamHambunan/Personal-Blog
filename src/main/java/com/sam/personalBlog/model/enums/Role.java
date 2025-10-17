package com.sam.personalBlog.model.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sam.personalBlog.model.enums.Permission.*;

public enum Role {
    USER(Set.of(BLOGS_READ)),
    ADMIN(Set.of(BLOGS_DELETE,BLOGS_UPDATE,BLOGS_WRITE,BLOGS_READ));

    private final Set<Permission> permissions;
    Role(Set<Permission> permissions)
    {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions()
    {
        return permissions;
    }

    public List<GrantedAuthority> getGrantedAuthorities()
    {
        List<GrantedAuthority> grantedAuthorities = this.getPermissions().stream()
                                                    .map(m -> new SimpleGrantedAuthority(m.getPermission()))
                                                    .collect(Collectors.toList());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;

    }

}
