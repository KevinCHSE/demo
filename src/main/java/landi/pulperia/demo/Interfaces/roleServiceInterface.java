package landi.pulperia.demo.Interfaces;

import java.util.List;

import org.hibernate.internal.util.Optional;

import landi.pulperia.demo.Entities.Login.Roles;

public interface roleServiceInterface {
        List<Roles>getUsers();
        Roles save(Roles roles);
        Optional<Roles> getRole(int  id);
        Optional<Roles>update(Roles roles, int  id);
        Optional<Roles>delate(int  id);

}
