package com.webapp.InvoiceManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name= "customer")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements UserDetails {
    //maybe use sequences?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @Column(name = "iban")
    private String iban;

    @Column(name = "bankName")
    private String bankName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    public Customer(String password, String email, Date created_at, Date updated_at, String iban, String bankName, Role role) {
        //this.customer_id = customer_id;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.iban = iban;
        this.bankName = bankName;
        this.role = role;
    }


    /*  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
        private List<Invoice> invoiceList;

        @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
        private List<Invoice> invoiceList;*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
