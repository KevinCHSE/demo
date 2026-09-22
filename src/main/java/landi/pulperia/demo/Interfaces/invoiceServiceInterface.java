package landi.pulperia.demo.Interfaces;

import java.util.List;
import java.util.Optional;

import landi.pulperia.demo.DTOs.InvoiceDTO;
import landi.pulperia.demo.Entities.Invoice;

public interface  invoiceServiceInterface {
    List<Invoice> invoiceList();
    Optional<Invoice> getInvoiceDetails(Integer id);
    Invoice save(InvoiceDTO invoiceDTO);
    Optional<Invoice>delate(int id );
}
