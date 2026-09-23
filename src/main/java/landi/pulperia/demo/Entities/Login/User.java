package landi.pulperia.demo.Entities.Login;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull 
    @NotBlank 
    @NotEmpty 
    @Column(name = "id")
    private String id;

    @NotNull 
    @NotBlank 
    @NotEmpty 
    private String name;

    @NotNull 
    @NotBlank 
    @NotEmpty 
    private String password;

    @NotNull 
    @NotBlank 
    @NotEmpty 
    private String phone;

    private boolean enable;

    @ManyToAny 
    @JoinTable(
        name="users_roles",
        joinColumns=@JoinColumn(name="user_id"),
        inverseJoinColumns =@JoinColumn(name="roles_id"),
        uniqueConstraints={@UniqueConstraint(columnNames={"user_id","roles_id"})}
    ) 
    private List<Roles>roles;

    @Transient
    private boolean Admin;

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

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
