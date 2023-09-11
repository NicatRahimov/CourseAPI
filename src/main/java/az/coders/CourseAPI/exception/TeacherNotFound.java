package az.coders.CourseAPI.exception;

public class TeacherNotFound extends RuntimeException{
    public TeacherNotFound(String message) {
        super(message);
    }
}
