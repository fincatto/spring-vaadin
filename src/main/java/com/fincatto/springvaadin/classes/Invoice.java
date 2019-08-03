package com.fincatto.springvaadin.classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Invoice implements Serializable {
    private Long id;
    private Integer numero;
    private Client cliente;
    private BigDecimal quantidade, valorUnitario;
    
    public Long getId() {
        return id;
    }
    
    public Invoice setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Integer getNumero() {
        return numero;
    }
    
    public Invoice setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }
    
    public Client getCliente() {
        return cliente;
    }
    
    public Invoice setCliente(Client cliente) {
        this.cliente = cliente;
        return this;
    }
    
    public BigDecimal getQuantidade() {
        return quantidade;
    }
    
    public Invoice setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }
    
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
    
    public Invoice setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
        return this;
    }
    
    public BigDecimal getValorTotal() {
        return this.quantidade != null && this.valorUnitario != null ? this.quantidade.multiply(this.valorUnitario) : null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(numero, invoice.numero) && Objects.equals(cliente, invoice.cliente) && Objects.equals(quantidade, invoice.quantidade) && Objects.equals(valorUnitario, invoice.valorUnitario);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, numero, cliente, quantidade, valorUnitario);
    }
    
    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", numero=" + numero + ", client=" + cliente + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + '}';
    }
}
