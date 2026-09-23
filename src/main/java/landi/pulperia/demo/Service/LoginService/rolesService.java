package landi.pulperia.demo.Service.LoginService;

import java.util.List;

import org.hibernate.internal.util.Optional;

import landi.pulperia.demo.Entities.Login.Roles;
import landi.pulperia.demo.Interfaces.roleServiceInterface;
import landi.pulperia.demo.Repositories.LoginRepository.RolesRepository;

public class rolesService implements roleServiceInterface {

    private final RolesRepository rolesRepository;

    public rolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Roles> getUsers() {
        return (List<Roles>) rolesRepository.findAll();
    }

    @Override
    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public Optional<Roles> getRole(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Roles> update(Roles roles, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Roles> delate(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
