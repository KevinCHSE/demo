package landi.pulperia.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import landi.pulperia.demo.DTOs.InvoiceDTO;
import landi.pulperia.demo.DTOs.PurchaseReport.PurchasesQuery;
import landi.pulperia.demo.Service.invoiceService;



@Controller 
@RequestMapping("/apiInvoice")
@CrossOrigin(origins="http://localhost:4200")
public class InvoiceController {

    private final invoiceService service;

    public InvoiceController(invoiceService service) {
        this.service = service;
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/saveInvoice")
    public ResponseEntity<?> save(@Valid @RequestBody InvoiceDTO invoiceDTO, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(invoiceDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getReport")
    public ResponseEntity<?> getReport(@Valid @ModelAttribute PurchasesQuery query, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.ok(service.getReport(query));
    }
    




    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors=new HashMap<>();

        result.getFieldErrors().forEach(error->{
            errors.put(error.getField(), "El campo "+error.getField()+" "+error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }


    
}
