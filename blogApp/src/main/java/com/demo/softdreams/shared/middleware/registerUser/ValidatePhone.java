//package com.demo.softdreams.shared.middleware.registerUser;
//
//import com.demo.softdreams.shared.res.RegisterResquest;
//import com.demo.softdreams.shared.respository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//public class ValidatePhone extends HandlerRegisterUser {
//    @Autowired
//    private  UserRepository dataSource;
//
//
//    @Override // check phone is exists
//    protected HasError checkProperties(RegisterResquest res) {
//        return dataSource.findByMsisdn(res.getMsisdn()).isPresent();
//    }
//
////    @Override
////    protected HasError hasError() {
////        return null;
////    }
//}