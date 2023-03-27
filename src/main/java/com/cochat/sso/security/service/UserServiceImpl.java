package com.cochat.sso.security.service;

import com.cochat.sso.security.entity.ChatUser;
import com.cochat.sso.security.model.UserDto;
import com.cochat.sso.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final boolean DEFAULT_ACC_NON_EXP = true;
    private static final boolean DEFAULT_CRED_NON_EXP = true;
    private static final boolean DEFAULT_ACC_NON_LOCKED = true;

    @Override
    public ChatUser create(UserDto userDto) {
        String passwd = userDto.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);

        ChatUser user = new ChatUser(
                userDto.getUsername(),
                encodedPassword,
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getEmail(),
                true,
                false,
                Set.of("USER")
        );
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        ChatUser user = userRepo.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found !"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isVerified(),
                DEFAULT_ACC_NON_EXP,
                DEFAULT_CRED_NON_EXP,
                DEFAULT_ACC_NON_LOCKED,
                user.getAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet())
        );
    }
}