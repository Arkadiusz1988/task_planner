package workshop.task_planner.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.task_planner.dto.UserDto;
import workshop.task_planner.entities.User;
import workshop.task_planner.repositories.UserRepository;
import workshop.task_planner.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDto> findAllDtoUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> user.toUserDto()).collect(Collectors.toList());
    }


}
