package workshop.task_planner.validations.user;


import org.springframework.beans.factory.annotation.Autowired;
import workshop.task_planner.dto.UserDto;
import workshop.task_planner.entities.User;
import workshop.task_planner.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidLoginAttemptValidator implements ConstraintValidator<ValidLoginAttempt, UserDto> {

  @Autowired
  UserRepository userRepository;

  public void initialize(ValidLoginAttempt constraint) {}

  public boolean isValid(UserDto loginAttempt, ConstraintValidatorContext context) {
    User user = userRepository.findByEmail(loginAttempt.getEmail());
    return user != null && user.passwordMatches(loginAttempt.getPassword());
  }
}
