package com.hussam.carsAuction.security.userService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hussam.carsAuction.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class to get logged-in user account details
 */
public class UserDetailsImp implements UserDetails {

    private Long id;

    private String firstName;
    private String email;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImp(Long id, String firstName, String email, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImp build(User user) {
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getType().name()))
                .collect(Collectors.toList());

        return new UserDetailsImp(
                user.getId(),
                user.getFirstName(), user.getEmail(),
                user.getPassword(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public Long getId() {
        return id;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
