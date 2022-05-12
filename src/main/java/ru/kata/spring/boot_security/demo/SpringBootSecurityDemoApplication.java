package ru.kata.spring.boot_security.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class SpringBootSecurityDemoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public SpringBootSecurityDemoApplication(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        roleService.addRole(new Role(1L,"ADMIN"));
        roleService.addRole(new Role(2L,"USER"));

        List<Role> role = new ArrayList<>();
        role.add(roleService.getRoleByRoleName("ADMIN"));
        role.add(roleService.getRoleByRoleName("USER"));

        List<Role> roleUser = new ArrayList<>();
        roleUser.add(roleService.getRoleByRoleName("USER"));

        User admin = new User( 1L,"admin" ,23,  "admin@mail.ru", "admin", role);

        User user = new User( 2L,"user", 42,  "user@mail.ru","user", roleUser);


        userService.saveUser(admin);
        userService.saveUser(user);

        openLoginPage();
    }

    private static void openLoginPage() {
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SpringApplicationBuilder configure (SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(SpringBootSecurityDemoApplication.class);
    }
}
