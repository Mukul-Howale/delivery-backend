package com.delivery.Delivery_app.service;

import com.delivery.Delivery_app.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomUserDetail implements UserDetails {

    private User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        System.out.println(user.getRoles().toString());
        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority(this.user.getRole()));
//       return user.getRoles().stream().map(role ->
//               new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toList());
        return set;
    }

    @Override
    public String getPassword() {
        System.out.println(user.getPassword()+" e");
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println(user.getEmailId()+" e");
        return user.getEmailId();
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
