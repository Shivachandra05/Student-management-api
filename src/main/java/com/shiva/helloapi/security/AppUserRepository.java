/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.shiva.helloapi.security.AppUser
 *  com.shiva.helloapi.security.AppUserRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.shiva.helloapi.security;

import com.shiva.helloapi.security.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository
extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findByUsername(String var1);

    public boolean existsByUsername(String var1);
}

