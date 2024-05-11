package com.demo.softdreams.core.sercurity;

import com.demo.softdreams.core.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // get info / fied of UserDetails
        return userRepository.findByUsername(username).orElseThrow(
                ()->{
                    throw new UsernameNotFoundException("Not Found User with Username "+username);
                }
        );



    }
}
