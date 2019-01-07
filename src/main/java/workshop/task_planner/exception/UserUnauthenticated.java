package workshop.task_planner.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthenticated extends RuntimeException {

    public UserUnauthenticated(String s) {
        super(s);
    }


}
