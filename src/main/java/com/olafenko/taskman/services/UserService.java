package com.olafenko.taskman.services;

import com.olafenko.taskman.exceptions.custom_exceptions.ResourceAlreadyTakenException;
import com.olafenko.taskman.models.AppUser;
import com.olafenko.taskman.models.dtos.auth.RegistrationRequest;
import com.olafenko.taskman.models.enums.Role;
import com.olafenko.taskman.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void register(RegistrationRequest registrationRequest) {

        if (userRepository.existsByEmail(registrationRequest.email()))
            throw new ResourceAlreadyTakenException("Email is taken!");

        if (userRepository.existsByUsername(registrationRequest.username()))
            throw new ResourceAlreadyTakenException("Username is taken!");

        String hashedPassword = passwordEncoder.encode(registrationRequest.password());

        AppUser appUser = AppUser.builder()
                .username(registrationRequest.username())
                .password(hashedPassword)
                .email(registrationRequest.email())
                .role(Role.USER).build();

        userRepository.save(appUser);

    }


}
