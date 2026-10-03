package landi.pulperia.demo.Interfaces;


import java.util.List;

import landi.pulperia.demo.DTOs.paymentQuery;
import landi.pulperia.demo.Entities.Payment;

public interface paymentServiceInterface {

    List<Payment>paymentListByID(String id);
    Payment savePayment(paymentQuery query);


}
