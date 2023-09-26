package de.telran.lesson3.domain_layer.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telran.lesson3.domain_layer.entity.Cart;
import de.telran.lesson3.domain_layer.entity.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "customer")
public class JpaCustomer implements Customer, UserDetails {

    public static final Logger LOGGER = LoggerFactory.getLogger(JpaCustomer.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, max = 15, message = "Customer name length must be between 3 and 15")
    @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    @NotBlank(message = "Аге must be not blank")
    @Min(value = 14)
    @Max(value = 99)
    private int age;

    @Email(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
    @NotBlank(message = "Email must be not blank")
    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private JpaCart cart;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_role",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public JpaCustomer() {
        LOGGER.info(String.format("The empty constructor is applied"));
    }

    public JpaCustomer(int id, String name) {
        LOGGER.info(String.format("The constructor with customer id - %d, and name - %s is applied", id, name));
        this.id = id;
        this.name = name;
    }

    public JpaCustomer(int id, String name, int age, String email) {
        LOGGER.info(String.format("The constructor with customer id - %d, name - %s, age - %d, and email - %s is applied",
                id, name, age, email));
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public JpaCustomer(int id, String name, String username, String password, int age, String email, JpaCart cart, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
        this.cart = cart;
        this.roles = roles;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public JpaCart getCart() {
        return cart;
    }

    public void setCart(JpaCart cart) {
        this.cart = cart;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}