package net.javaguides.todo.config;

import lombok.AllArgsConstructor;
import net.javaguides.todo.security.JwtAuthenticationEntryPoint;
import net.javaguides.todo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity //TO provide Method Level Security
public class SpringSecurityConfig {

    //Below we are Injecting constructor based dependencies
    //Below We didn't create constructors manually we used Lombok Annotations
    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    //Below is Basic Authentication with headers, hackers can find Username, password using online tool
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) ->{
                    //Below are Role Based Authorization

                    //all the incoming http post request that starts with the /api should be accessible
                    // by all the users who have admin role.
//            authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    authorize.requestMatchers("/api/auth/**").permitAll();

                    //Below we Handled Preflight request in Spring Boot
                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean  //Multi-user's to perform Basic Authentication (InMemoryAuthentication)
//    public UserDetailsService userDetailsService(){
//
//        UserDetails mani = User.builder()
//                .username("mani")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin =User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(mani, admin);
//    }
}
