package landi.pulperia.demo.Interfaces;

import java.util.List;

import landi.pulperia.demo.DTOs.UserDTO;
import landi.pulperia.demo.Entities.Login.User;

public interface UserServiceInterface {
        List<UserDTO>getUsers();
        UserDTO save(User user);

}
