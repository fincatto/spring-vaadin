package com.fincatto.springvaadin.repositories;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.Client;
import com.fincatto.springvaadin.classes.Invoice;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientRepository implements Loggable {
    
    private static final int MAX_ITENS = 100;
    
    public List<Client> findAll() {
        getLogger().debug("Buscando todos...");
        final List<Client> clients = new ArrayList<>(MAX_ITENS);
        for (long i = 1; i <= MAX_ITENS; i++) {
            clients.add(new Client().setId(i).setNome(String.format("Client %s", i)).setEmail(String.format("client%s@gmail.com", i)));
        }
        return clients;
    }
    
    public List<Client> findByOffset(int offset, int limit) {
        getLogger().debug("Buscando de {} ate {}...", offset, limit);
        final List<Client> clients = new ArrayList<>();
        for (long i = offset + 1; i <= limit + offset; i++) {
            clients.add(new Client().setId(i).setNome(String.format("Client %s", i)).setEmail(String.format("client%s@gmail.com", i)));
        }
        return clients;
    }
    
    public int count() {
        return MAX_ITENS;
    }
    
    public List<Client> findByName(String pattern) {
        getLogger().debug("Buscando {}...", pattern);
        return this.findAll().stream().filter(u -> u.getNome().toLowerCase().startsWith(pattern.toLowerCase())).collect(Collectors.toList());
    }
    
    public Client findById(long id) {
        getLogger().debug("Buscando cliente {}...", id);
        final Client client = new Client().setId(id).setEmail("client%teste.com").setNome("Test Client").setInvoices(new ArrayList<>(10));
        for (int i = 1; i <= 100; i++) {
            client.getInvoices().add(new Invoice().setId((long) i).setCliente(client).setNumero(i).setQuantidade(new BigDecimal(i)).setValorUnitario(BigDecimal.TEN.add(new BigDecimal(i))));
        }
        return client;
    }
}
