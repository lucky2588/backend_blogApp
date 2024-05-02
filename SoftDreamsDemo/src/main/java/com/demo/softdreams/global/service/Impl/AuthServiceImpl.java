package com.demo.softdreams.global.service.Impl;

import com.demo.softdreams.core.dto.mapper.UserMapper;
import com.demo.softdreams.core.entites.Role;
import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.core.sercurity.CustomUserDetailService;
import com.demo.softdreams.core.sercurity.JwtUtils;
import com.demo.softdreams.global.service.AuthService;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.middleware.registerUser.HandlerRegisterUser;
import com.demo.softdreams.shared.middleware.registerUser.ValidationUser;
import com.demo.softdreams.shared.res.AuthRes;
import com.demo.softdreams.shared.res.LoginResquest;
import com.demo.softdreams.shared.res.RegisterResquest;
import com.demo.softdreams.shared.respository.RoleRepository;
import com.demo.softdreams.shared.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService customUserDetailsService;
    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;
    private  HandlerRegisterUser handlerValidation;
    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private RoleRepository roleRepository;

    public AuthRes login(LoginResquest resquestLogin) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(resquestLogin.getUsername(), resquestLogin.getPassword());
        // tien hanh xac thuc
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            //  Luu vao database Context Horder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Lấy ra thông tin UserDetails()
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
            String jwtToken = jwtUtils.generateToken(userDetails);
            User user = userRepository.findByUsername(authentication.getName()).orElse(null);

            return
                    new AuthRes(
                            UserMapper.toUserDto(user),
                            jwtToken, true
                    );
        } catch (Exception ex) {
            throw new BadCredentialsException(ex.getMessage());
        }
    }

    // Đăng kí tài khoản => todo : check tài khoản với email , tạo user và gửi về mã token để xác nhận
    public ResponseEntity<?> registerAccount(RegisterResquest resquest) {
        handlerValidation = HandlerRegisterUser.setNextChain(new ValidationUser(this.userRepository));
        if(handlerValidation.checkProperties(resquest).isError){
            log.info(handlerValidation.checkProperties(resquest).mess);
            throw new BadResquestException(handlerValidation.checkProperties(resquest).mess);
        }
//        if(!resquest.getPassword().equals(resquest.getReTypePassword())){
//            throw new BadResquestException("Password and rePassword not vail !! ");
//        }
//        Optional<User> users = userRepository.findByUsername(resquest.getUsername());
//        if (users.isPresent()) { // nếu đã có tài khoản
//            throw new BadResquestException("Username exits");
//        }
//        Optional<User> isCheckEmail = userRepository.findByEmail(resquest.getEmail());
//        if (isCheckEmail.isPresent()) { // nếu đã có email
//            throw new BadResquestException("Email exits");
//        }
//        Optional<User> isCheckMissr = userRepository.findByMsisdn(resquest.getMsisdn());
//        if (isCheckMissr.isPresent()) { // nếu đã có midss
//            throw new BadResquestException(" Numbers exits !! ");
//        }
        String gender = "";
       switch (resquest.getGender()){
           case SharedConstance.GENDER.MALE:
               gender = SharedConstance.GENDER.MALE;
               break;
           case SharedConstance.GENDER.FEMALE:
               gender = SharedConstance.GENDER.FEMALE;
               break;
           default:
               gender = SharedConstance.GENDER.OTHER;
               break;
       }
        Date currentDate = new Date();
        String forAge = "";
        if ((currentDate.getYear() -  resquest.getBirthday().getYear()) > 18){
            forAge = SharedConstance.AGE_TYPE.ADULT;
        }else{
            forAge = SharedConstance.AGE_TYPE.CHILDREN;
        }
        Role role = roleRepository.findByRoleName("USER").orElse(null);
        User user = User.builder()
                .email(resquest.getEmail())
                .msisdn(resquest.getMsisdn())
                .username(resquest.getUsername())
                .name(resquest.getName())
                .deleted(false)
                .forAge(forAge)
                .avatar(SharedConstance.IMAGE_TYPE.UNAVAILABLE)
                .gender(gender)
                .birthday(resquest.getBirthday())
                .address(resquest.getAddress())
                .password(encoder.encode(resquest.getPassword()))
                .roles(List.of(role))
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("Create User Success !! ");
    }




}
