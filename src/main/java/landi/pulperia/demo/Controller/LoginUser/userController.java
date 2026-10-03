package landi.pulperia.demo.Controller.LoginUser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import landi.pulperia.demo.Entities.Login.User;
import landi.pulperia.demo.Service.LoginService.UserService;


@RestController 
@RequestMapping("/User")
public class userController {

    private final UserService service;

    public userController(UserService service) {
        this.service = service;
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/saveUser")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody User user, BindingResult result) {

        if(result.hasFieldErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(user)) ;
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors=new HashMap<>();

        result.getFieldErrors().forEach(error->{
            errors.put(error.getField(), "El campo "+error.getField()+" "+error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    

}
