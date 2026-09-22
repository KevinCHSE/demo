package landi.pulperia.demo.Service.LoginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import landi.pulperia.demo.DTOs.UserDTO;
import landi.pulperia.demo.Entities.Login.Roles;
import landi.pulperia.demo.Entities.Login.User;
import landi.pulperia.demo.Interfaces.UserServiceInterface;
import landi.pulperia.demo.Repositories.LoginRepository.RolesRepository;
import landi.pulperia.demo.Repositories.LoginRepository.UserRepository;

@Service 
public class UserService implements UserServiceInterface{

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    

    

    public UserService(UserRepository userRepository, RolesRepository rolesRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User>users=(List<User>) userRepository.findAll();

        return  users.stream()
        .map(user-> new UserDTO(user))
        .toList();
    }

    @Override
    public UserDTO save(User user) {
        Optional<Roles>optionalRole= rolesRepository.findByRole("ROLE_USER");
        List<Roles>roles= new ArrayList<>();
        optionalRole.ifPresent(role->roles.add(role));

        if(user.isAdmin()==true){
            Optional<Roles>optionalRoleAdmin= rolesRepository.findByRole("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(role->roles.add(role));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new UserDTO(user);
    }

    @Override
    public Optional<UserDTO> getClient(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClient'");
    }

    @Override
    public Optional<UserDTO> update(UserDTO userDTO, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<UserDTO> delate(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delate'");
    }


}
