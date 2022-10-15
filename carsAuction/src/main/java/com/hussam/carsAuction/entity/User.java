package com.hussam.carsAuction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Pojo class for user entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "FirstName is required")
    @Column(name = "firstName", nullable = false, length = 30)
    private String firstName;
    @NotBlank(message = "LastName is required")
    @Column(name = "lastName", nullable = false, length = 30)
    private String lastName;
    @Email(regexp ="^[A-Za-z0-9+_.-]+@(.+)$", message = "Please enter a valid email!!")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name="password", nullable = false)
    @Size(min = 8 ,message = "Password must be more than 8 characters")
    @JsonIgnore
    private String password;
    @Column(length = 10)
    private Long phone;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Car> car;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Bid> bids;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BankAccount bankAccount;
}
