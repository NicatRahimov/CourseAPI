package az.coders.CourseAPI.config;

import az.coders.CourseAPI.exception.GroupIsFull;
import az.coders.CourseAPI.exception.GroupNotFound;
import az.coders.CourseAPI.exception.StudentNotFound;
import az.coders.CourseAPI.exception.TeacherNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TeacherNotFound.class)
    public ResponseEntity<String> handleCustomException(TeacherNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<String> handleCustomException(StudentNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(GroupNotFound.class)
    public ResponseEntity<String> handleCustomException(GroupNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(GroupIsFull.class)
    public ResponseEntity<String> handleCustomException(GroupIsFull ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
