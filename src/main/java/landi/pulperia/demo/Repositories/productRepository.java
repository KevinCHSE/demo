package landi.pulperia.demo.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import landi.pulperia.demo.Entities.Products;

public interface productRepository extends CrudRepository<Products, Integer>{
    @Query( value="SELECT count(stock) FROM products where stock<=5", nativeQuery=true)
    int needStock();
}
