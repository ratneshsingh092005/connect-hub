package com.ratnesh.connecthub.user_service.Service;

import com.ratnesh.connecthub.commonlib.security.JwtUserPrincipal;
import com.ratnesh.connecthub.user_service.entity.User;
import com.ratnesh.connecthub.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new JwtUserPrincipal(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );    }
}
