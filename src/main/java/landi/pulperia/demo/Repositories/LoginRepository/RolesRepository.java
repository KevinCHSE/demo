package landi.pulperia.demo.Repositories.LoginRepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import landi.pulperia.demo.Entities.Login.Roles;


public interface RolesRepository extends CrudRepository<Roles, Integer>{

    Optional<Roles> findByRole(String role);

}
