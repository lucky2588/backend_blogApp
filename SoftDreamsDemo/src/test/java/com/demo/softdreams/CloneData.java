//package com.demo.softdreams;
//
//
//import com.demo.softdreams.core.entites.Blog;
//import com.demo.softdreams.core.entites.Role;
//import com.demo.softdreams.core.entites.User;
//import com.demo.softdreams.shared.common.SharedConstance;
//import com.demo.softdreams.shared.respository.BlogRepository;
//import com.demo.softdreams.shared.respository.RoleRepository;
//import com.demo.softdreams.shared.respository.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AllArgsConstructor
//@Rollback(value = false)
//public class CloneData{
//
//    @Autowired
//    private BlogRepository dataSource;
//    @Autowired
//    private PasswordEncoder encoder = new BCryptPasswordEncoder();
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    @Autowired
//    private RoleRepository roleRepository;
//
////    @Test
////    void save_users() {
////        Blog blog = new Blog();
////        blog.setDeleted(true);
////        blog.setContent("1111");
////        dataSource.save(blog);
//
//    public CloneData() {
//    }
////    }
//
//    @Test
//    void save_users() {
//        Role roleUser = roleRepository.findByRoleName("USER").orElse(null);
//        Role roleAdmin = roleRepository.findByRoleName("ADMIN").orElse(null);
//        Role roleAuthor = roleRepository.findByRoleName("AUTHOR").orElse(null);
//        User user = User.builder()
//                .address("Thuong tin")
//                .email("lucky@123")
//                .gender(SharedConstance.GENDER.MALE)
//                .forAge(SharedConstance.AGE_TYPE.ALL)
//                .username("lucky@softdreams")
//                .name("Duc Thang")
//                .avatar(" None ")
//                .roles(List.of(roleAdmin))
//                .password(passwordEncoder.encode("123456"))
//                .build();
//        userRepository.save(user);
////
//    }
//
//
//
//
//
//
//}