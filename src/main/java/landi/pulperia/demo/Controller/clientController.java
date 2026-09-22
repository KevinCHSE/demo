package landi.pulperia.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import landi.pulperia.demo.Entities.Client;
import landi.pulperia.demo.Service.clientService;






@RestController 
@RequestMapping("/apiClients")
@CrossOrigin(origins = "http://localhost:4200")
public class clientController {

    private final clientService service;

    

    public clientController(clientService service) {
        this.service = service;
    }



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getClients")
    public List<Client> getClients() {
        return service.clientList();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/clientCount")
    public int clientCount() {
        return service.clientCount();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveClient")
    public ResponseEntity<?>saveClient(@Valid @RequestBody Client client, BindingResult result) {
        if(result.hasFieldErrors()){
            return  validation(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(client));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateClient/{id}")
    public ResponseEntity<?>updateClient(@PathVariable String id, @Valid @RequestBody Client client, BindingResult result) {
        if(result.hasFieldErrors()){
            return  validation(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(client, id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getClient/{id}")
    public Optional<Client> getClient(@PathVariable String id) {
        return service.getClient(id);
    }
    


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors=new HashMap<>();

        result.getFieldErrors().forEach(error->{
            errors.put(error.getField(), "El campo "+error.getField()+" "+error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
        

}
