package com.shiva.helloapi.security;

import com.shiva.helloapi.security.AppUser;
import com.shiva.helloapi.security.AppUserDetails;
import com.shiva.helloapi.security.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService
implements UserDetailsService {
    private final AppUserRepository apr;

    public AppUserDetailsService(AppUserRepository apr) {
        this.apr = apr;
    }

    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        AppUser apu = (AppUser)this.apr.findByUsername(Username).orElseThrow(() -> new UsernameNotFoundException("username not found+Username"));
        AppUserDetails apur = new AppUserDetails(apu);
        return apur;
    }
}

