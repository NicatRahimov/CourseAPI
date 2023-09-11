package az.coders.CourseAPI.exception;

public class GroupIsFull extends RuntimeException{
    public GroupIsFull(String message){
        super(message);
    }
}
