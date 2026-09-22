package landi.pulperia.demo.Entities;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Client {
    @Id
    @NotEmpty
    @NotBlank
    private String id;

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String phone;
    private Integer creditLimit;
    private Integer balance;
    private boolean active=true;

    public Client() {
    }

    public Client(String id, @NotEmpty @NotBlank String name, @NotEmpty @NotBlank String phone, Integer creditLimit,
            Integer balance, boolean active) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.balance = balance;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
   

   