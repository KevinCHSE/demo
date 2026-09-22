package landi.pulperia.demo.Interfaces;

import java.util.List;
import java.util.Optional;

import landi.pulperia.demo.Entities.Client;

public interface clientServiceInterface {
    List<Client> clientList();
    Optional<Client> getClient(String id);
    Client save(Client client);
    Optional<Client>update(Client Client, String id);
    Optional<Client>delate(String id);
    int clientCount();
}
