package landi.pulperia.demo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import landi.pulperia.demo.DTOs.paymentQuery;
import landi.pulperia.demo.Entities.Client;
import landi.pulperia.demo.Entities.Payment;
import landi.pulperia.demo.Interfaces.paymentServiceInterface;
import landi.pulperia.demo.Repositories.clientRepository;
import landi.pulperia.demo.Repositories.paymentRepository;
;

@Service 
public class paymentService implements paymentServiceInterface {

    private final paymentRepository paymentRepository;
    private clientRepository clientRepository;
    
    
    public paymentService(landi.pulperia.demo.Repositories.paymentRepository paymentRepository,
            landi.pulperia.demo.Repositories.clientRepository clientRepository) {
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Payment> paymentListByID(String id) {
        Optional<Client>optionalClient= clientRepository.findById(id);
        if(optionalClient.isEmpty()){
            return null;
        }

        return paymentRepository.listPaymentsByClient(id);
    }


    @Override
    @Transactional 
    public Payment savePayment(paymentQuery query) {
        Client client= clientRepository.findById(query.getClientId()).orElseThrow();

        if (query.getAmount()<0 || query.getAmount()>client.getUsedCredit()) {
            throw new IllegalArgumentException("El monto es inválido o supera lo que debe el cliente");
        }

        Payment payment=new Payment();
        payment.setClient(client);
        payment.setStarDate(LocalDate.parse(query.getStartDate()));
        payment.setEndDate(LocalDate.parse(query.getEndDate()));
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(query.getAmount());
        payment.setPriviuosBalance(client.getUsedCredit());
        payment.setNewBalance(client.getUsedCredit()-query.getAmount());

        int result=client.getUsedCredit()-query.getAmount();
        client.setUsedCredit(result);

        return paymentRepository.save(payment);
    }
    



     
    


}
