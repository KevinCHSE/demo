package landi.pulperia.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import landi.pulperia.demo.Interfaces.invoiceDetailsServiceInterface;
import landi.pulperia.demo.Repositories.invoiceDetailsRepository;


@Service 
public class invoiceDetailsService implements invoiceDetailsServiceInterface{

    private final invoiceDetailsRepository repository;

    public invoiceDetailsService(invoiceDetailsRepository repository) {
        this.repository = repository;
    }



    @Override
    @Transactional(readOnly=true)
    public int getTotal() {
        return repository.getTotal();
    }

    

}
