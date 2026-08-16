package com.ratnesh.connecthub.user_service.Service;

import com.ratnesh.connecthub.commonlib.error.BadRequestException;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
import com.ratnesh.connecthub.commonlib.security.JwtUserPrincipal;
import com.ratnesh.connecthub.user_service.dto.AuthResponse;
import com.ratnesh.connecthub.user_service.dto.LoginRequest;
import com.ratnesh.connecthub.user_service.dto.SignupRequest;
import com.ratnesh.connecthub.user_service.dto.UserProfileResponse;
import com.ratnesh.connecthub.user_service.entity.User;
import com.ratnesh.connecthub.user_service.mapper.UserMapper;
import com.ratnesh.connecthub.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByEmail(request.email()).ifPresent(user -> {
            throw new BadRequestException("User already exists with email: "+request.email());
        });
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        user = userRepository.save(user);
        JwtUserPrincipal jwtUserPrincipal = new JwtUserPrincipal(user.getId(),user.getName(),user.getEmail(),null);

        String accessToken = authUtil.generateAccessToken(jwtUserPrincipal);
        return new AuthResponse(accessToken,userMapper.toUserProfileResponse(jwtUserPrincipal));

    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),request.password()
                )
        );

        JwtUserPrincipal user = (JwtUserPrincipal) authentication.getPrincipal();

        String accessToken = authUtil.generateAccessToken(user);


        return new AuthResponse(accessToken,userMapper.toUserProfileResponse(user));
    }
}
