package landi.pulperia.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import landi.pulperia.demo.DTOs.paymentQuery;
import landi.pulperia.demo.Service.paymentService;



@RestController 
@RequestMapping("/apiPayment")
public class paymentController {
    private final paymentService paymentService;

    public paymentController(landi.pulperia.demo.Service.paymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/getPaymentReports/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentService.paymentListByID(id));
    }
    

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/savePayment")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody paymentQuery paymentQuery, BindingResult result) {
        if(result.hasFieldErrors()){
            return  validation(result);
        }
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentService.savePayment(paymentQuery));
    }



    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors=new HashMap<>();

        result.getFieldErrors().forEach(error->{
            errors.put(error.getField(), "El campo "+error.getField()+" "+error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
}
