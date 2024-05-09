package com.spa.Online.Spa.service;

import com.spa.Online.Spa.domain.USER_ROLE;
import com.spa.Online.Spa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.spa.Online.Spa.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class  CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Email Id not found" +username);
        }
        USER_ROLE role = user.getRole();
//SAI LOGIC code dan that su :)))))))
        if (role == null)role=USER_ROLE.ROLE_CUSTOMER;
        List<GrantedAuthority> authorities=new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
