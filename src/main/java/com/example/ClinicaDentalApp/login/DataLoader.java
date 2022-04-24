package com.example.ClinicaDentalApp.login;

import com.example.ClinicaDentalApp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private AppUserRepository userRepository;

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        String hashedPassword2 = passwordEncoder.encode("password2");
        userRepository.save(new AppUser("Gaston","DrCooper","gaston@gmail.com",hashedPassword,AppUserRole.ADMIN));
        userRepository.save(new AppUser("Micaela","Mica","mica@gmail.com",hashedPassword2,AppUserRole.ADMIN));
    }
}
