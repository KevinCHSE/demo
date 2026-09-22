package landi.pulperia.demo.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import landi.pulperia.demo.Entities.Client;

public interface  clientRepository extends  CrudRepository<Client, String>{
    
    @Query(value="select count(id) from pulperia_landi.client where active=1", nativeQuery=true)
    public int getClientCount();   
}
