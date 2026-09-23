package landi.pulperia.demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import landi.pulperia.demo.Service.invoiceDetailsService;


@RestController 
@RequestMapping("/apiInoviceDetails")

public class invoiceDetailsController {

    private final invoiceDetailsService service;

    public invoiceDetailsController(invoiceDetailsService service) {
        this.service = service;
    }



    @GetMapping("/getTotal")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public int getTotal() {
        return service.getTotal();
    }
    

}
