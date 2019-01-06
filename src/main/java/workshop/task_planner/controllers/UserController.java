package workshop.task_planner.controllers;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import workshop.task_planner.dto.UserDto;
import workshop.task_planner.entities.User;
import workshop.task_planner.exception.UserUnauthenticated;
import workshop.task_planner.repositories.UserRepository;
import workshop.task_planner.service.AuthService;
import workshop.task_planner.validations.user.LoginAttemptValidationGroup;
import workshop.task_planner.validations.user.RegistrationAttemptValidationGroup;


@RestController
public class UserController {


  private final AuthService authService;
  private final UserRepository userRepository;

  public UserController(AuthService authService, UserRepository userRepository) {
    this.authService = authService;
    this.userRepository = userRepository;
  }


  @GetMapping
  public User getBasicInfo() {
    if (!authService.isUserLoggedIn()) {
      throw new UserUnauthenticated("user not logged");
    }
    return authService.getUser();
  }

//
//  @GetMapping("/login")
//  public String showLoginForm(Model model) {
//    if (authService.isUserLoggedIn()) {
//      return "redirect:/";
//    }
//    model.addAttribute("user", new UserDto());
//    return "forms/login";
//  }

  @PostMapping("/login")
  public User login(
      @Validated(LoginAttemptValidationGroup.class) @RequestBody UserDto loginAttempt) {
      return  authService.setUser(userRepository.findByEmail(loginAttempt.getEmail()));
  }

  @PostMapping("/register")
  public User register(@Validated(RegistrationAttemptValidationGroup.class) @RequestBody UserDto registrationAttempt) {
    User newUser = userRepository.save(registrationAttempt.toUser());
    authService.setUser(newUser);
    return newUser;
  }

  @PostMapping("/logout")
  public void logout() {
    authService.logout();
  }

  @DeleteMapping("/delete/{id}")
  public User deleteUser(@PathVariable Long id){
    User user = userRepository.getOne(id);
    if (!(authService.isUserLoggedIn())) {
      throw new UserUnauthenticated("user not logged or is not Admin");
    }
    userRepository.delete(user);
    return user;
  }

}
