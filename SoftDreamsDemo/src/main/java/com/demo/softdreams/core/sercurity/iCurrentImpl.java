package com.demo.softdreams.core.sercurity;

import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class iCurrentImpl implements iCurrent{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName()).orElseThrow(
                ()->{
                    throw new BadResquestException("Not found User with Username " +authentication.getName());
                }
        );

    }
}
