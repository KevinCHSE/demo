package landi.pulperia.demo.DTOs;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class InvoiceDTO {

    @NotEmpty
    @NotBlank  
    private String clientId;
    @NotEmpty
    @NotBlank
    private String payment;
    private List<InvoiceDetailsDTO>items;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<InvoiceDetailsDTO> getItems() {
        return items;
    }







}
