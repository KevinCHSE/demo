package landi.pulperia.demo.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import landi.pulperia.demo.Entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

    @Query(value = """
        SELECT * FROM invoice
        WHERE client_id = :clientId
        AND date BETWEEN :startDate AND :endDate order by date desc
        """, nativeQuery = true)
    List<Invoice> getReport(
        @Param("clientId") String clientId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );
}
