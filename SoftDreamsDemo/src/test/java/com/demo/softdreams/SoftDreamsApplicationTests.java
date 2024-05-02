package com.demo.softdreams;

import com.demo.softdreams.core.entites.Role;
import com.demo.softdreams.core.entites.User;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.respository.RoleRepository;
import com.demo.softdreams.shared.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SoftDreamsApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private PasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private RoleRepository roleRepository;



	    @Test
        void save_infoAdmin() {
        Role roleUser = roleRepository.findByRoleName("USER").orElse(null);
        Role roleAdmin = roleRepository.findByRoleName("ADMIN").orElse(null);
			User user = User.builder()
					.address("Thuong tin")
					.email("thang@123")
					.gender(SharedConstance.GENDER.MALE)
					.forAge(SharedConstance.AGE_TYPE.ALL)
					.username("thang@softdreams")
					.name("Khong Ten")
					.avatar(" None ")
					.roles(List.of(roleAdmin))
					.password(passwordEncoder.encode("123456"))
					.build();
        userRepository.save(user);
    }

	@Test
	void save_infoUser() {
		Role roleUser = roleRepository.findByRoleName("USER").orElse(null);
		User user = User.builder()
				.address("Thuong tin")
				.email("luckyyy@123")
				.gender(SharedConstance.GENDER.MALE)
				.forAge(SharedConstance.AGE_TYPE.ALL)
				.username("luckyyy@softdreams")
				.name("Duc Thang")
				.avatar(" None ")
				.roles(List.of(roleUser))
				.password(passwordEncoder.encode("123456"))
				.build();
		userRepository.save(user);
	}

	@Test
	void save_role(){
			Role roleAdmin = new Role();
		roleAdmin.setRoleCode("ADMIN");
		roleAdmin.setRoleName("ADMIN");
			roleRepository.save(roleAdmin);


		Role roleUser = new Role();
		roleUser.setRoleCode("USER");
		roleUser.setRoleName("USER");
		roleRepository.save(roleUser);
	}

}
