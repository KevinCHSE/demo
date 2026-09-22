package landi.pulperia.demo.DTOs;

import java.util.List;

import landi.pulperia.demo.Entities.Login.Roles;
import landi.pulperia.demo.Entities.Login.User;

public class UserDTO {  
   
    private String id;

    private String name;

    private String phone;

    private boolean enable;


    private List<Roles>roles;

    

    public UserDTO(User user) {
        this.id=user.getId();
        this.name=user.getName();
        this.phone=user.getPhone();
        this.enable=user.isEnable();
        this.roles=user.getRoles();
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


    public boolean isEnable() {
        return enable;
    }


    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    public List<Roles> getRoles() {
        return roles;
    }


    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    
}
