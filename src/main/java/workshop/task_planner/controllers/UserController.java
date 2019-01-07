package workshop.task_planner.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import workshop.task_planner.dto.UserDto;
import workshop.task_planner.entities.User;
import workshop.task_planner.exception.UserUnauthenticated;
import workshop.task_planner.repositories.UserRepository;
import workshop.task_planner.service.AuthService;
import workshop.task_planner.service.UserService;
import workshop.task_planner.validations.user.LoginAttemptValidationGroup;
import workshop.task_planner.validations.user.RegistrationAttemptValidationGroup;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

  private final AuthService authService;
  private final UserRepository userRepository;
  private final UserService userService;

  @Autowired
  public UserController(AuthService authService, UserRepository userRepository, UserService userService) {
    this.authService = authService;
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @GetMapping("/")
  public List<UserDto> usersList() {
    return userService.findAllDtoUser();
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
    //Task task = new Task();
    //task
    userRepository.delete(user);
    return user;
  }

}
// zrobic token w userze uuid token String klasa UUID zeby wygenerowac String token, robimy save na znalezionym uzytkowniku, z tokenem, token robiony za kazdym razem nowy, tylko przy logwaniu - zwracamy login z tokenem(loginDTo) potem ten token w postmanie kopiujemy do headera, uzytoryzation i token, potem odbieramy sciagamy @RequestHeader do zmiennej String, do servisu dodajemy Token, w authService find by token dla uzytkownika, sprawdzamy dla otrzymanego tokena, czy istnieje