package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.dto.UserEntityDto;
import az.coders.CourseAPI.model.UserEntity;
import az.coders.CourseAPI.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    @PostMapping("login")
    public String addUser(UserEntity userEntity){
        return authService.getToken(userEntity);
    }
}
