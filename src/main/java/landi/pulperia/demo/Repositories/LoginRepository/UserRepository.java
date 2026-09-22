package landi.pulperia.demo.Repositories.LoginRepository;

import org.springframework.data.repository.CrudRepository;

import landi.pulperia.demo.Entities.Login.User;

public interface  UserRepository extends CrudRepository<User, String>{

}
