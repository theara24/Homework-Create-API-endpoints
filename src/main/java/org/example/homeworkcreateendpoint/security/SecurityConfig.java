//package kh.edu.cstad.mbapi.security;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    private final String ROLE_ADMIN = "ADMIN";
//    private final String ROLE_STAFF = "STAFF";
//    private final String ROLE_CUSTOMER = "CUSTOMER";
//
////    private final String PASSWORD = "1234";
//
////    @Bean
////    public InMemoryUserDetailsManager configureUsers(){
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password(passwordEncoder.encode(PASSWORD))
////                .roles(ROLE_ADMIN)
////                .build();
////
////        UserDetails staff = User.builder()
////                .username("staff")
////                .password(passwordEncoder.encode(PASSWORD))
////                .roles(ROLE_STAFF)
////                .build();
////
////        UserDetails customer = User.builder()
////                .username("customer")
////                .password(passwordEncoder.encode(PASSWORD))
////                .roles(ROLE_CUSTOMER)
////                .build();
////
////
////        manager.createUser(admin);
////        manager.createUser(staff);
////        manager.createUser(customer);
////
////        return manager;
////    }
//
////    public DaoAuthenticationProvider daoAuthProvider(){
////        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////    }
////
//    @Bean
//    public SecurityFilterChain configureApiSecurity(HttpSecurity http) throws Exception {
//
//        // TODO
//        // make all endpoints secured
//        http.authorizeHttpRequests(endpoints -> endpoints
//                .requestMatchers(HttpMethod.POST,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN,ROLE_STAFF)
//                .requestMatchers(HttpMethod.PUT,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN,ROLE_STAFF)
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN)
//                .requestMatchers(HttpMethod.GET,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN,ROLE_STAFF,ROLE_CUSTOMER)
//                .requestMatchers("/api/v1/accounts/**").hasAnyRole(ROLE_ADMIN,ROLE_STAFF,ROLE_CUSTOMER)
//                .anyRequest()
//                .authenticated()
//        );
//
//        // set security mechanism
//        http.httpBasic(Customizer.withDefaults());
//
//        // disable form login of web
////        http.formLogin(form -> form.disable());
//        http.formLogin(AbstractHttpConfigurer::disable);
//
//        // CSRF common protection -> CSRF token
////        http.csrf(token -> token.disable());
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        // make stateless API
//        http.sessionManagement(session ->
//                session.sessionCreationPolicy(
//                        SessionCreationPolicy.STATELESS
//                ));
//
//        return http.build();
//
//    }
//
//}