package com.shiva.helloapi.security;

import com.shiva.helloapi.security.AppUser;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails
implements UserDetails {
    private final AppUser apr;

    public AppUserDetails(AppUser apr) {
        this.apr = apr;
    }

    public String getPassword() {
        return this.apr.getPassword();
    }

    public String getUsername() {
        return this.apr.getUsername();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.apr.getRole() == null || this.apr.getRole().isBlank()) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority(this.apr.getRole()));
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }
}

