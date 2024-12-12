package org.example.langeureka.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {
    @Bean
    fun bCryptPasswordEncoder() : BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { auth: CsrfConfigurer<HttpSecurity> -> auth.disable() }

        http.authorizeHttpRequests(Customizer { auth ->
                auth.anyRequest().authenticated()
            })

        http.httpBasic(Customizer.withDefaults())

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user1: UserDetails = User.builder()
            .username("admin")
            .password(bCryptPasswordEncoder().encode("1234"))
            .roles("ADMIN")
            .build()


        return InMemoryUserDetailsManager(user1)
    }
}