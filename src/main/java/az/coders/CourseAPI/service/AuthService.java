package az.coders.CourseAPI.service;

import az.coders.CourseAPI.model.UserEntity;
import az.coders.CourseAPI.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final JwtUtil jwtUtil;

    public String getToken(UserEntity userEntity) {
        return  jwtUtil.generateToken(userEntity);
    }
}