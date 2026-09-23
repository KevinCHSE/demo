package landi.pulperia.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import landi.pulperia.demo.Entities.invoiceDetails;

public interface invoiceDetailsRepository extends CrudRepository<invoiceDetails, Integer>{
    @Query(value="select sum(price) as total from invoice_details", nativeQuery=true)
    int getTotal();


    @Query(value="select * from invoice_details where invoice_id= :id", nativeQuery=true)
    List<invoiceDetails> getDetailsByInvoice(@Param("id") int invoice );

}
