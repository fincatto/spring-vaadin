package com.fincatto.springvaadin.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Client implements Serializable {
    
    private Long id;
    private String nome, email;
    private List<Invoice> invoices;
    
    public Long getId() {
        return id;
    }
    
    public Client setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getNome() {
        return nome;
    }
    
    public Client setNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Client setEmail(String email) {
        this.email = email;
        return this;
    }
    
    public List<Invoice> getInvoices() {
        return invoices;
    }
    
    public Client setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client user = (Client) o;
        return id == user.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nome='" + nome + '\'' + ", email='" + email + '\'' + '}';
    }
}
