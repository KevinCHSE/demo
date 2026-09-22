package landi.pulperia.demo.Interfaces;

import java.util.List;
import java.util.Optional;

import landi.pulperia.demo.DTOs.UserDTO;
import landi.pulperia.demo.Entities.Login.User;

public interface UserServiceInterface {
        List<UserDTO>getUsers();
        UserDTO save(User user);
        Optional<UserDTO> getClient(String id);
        Optional<UserDTO>update(UserDTO userDTO, String id);
        Optional<UserDTO>delate(String id);

}
