package com.fincatto.springvaadin.repositories;

import com.fincatto.springvaadin.classes.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private static final int MAX_ITENS = 100;

    public List<User> findAll() {
        final List<User> users = new ArrayList<>(MAX_ITENS);
        for (int i = 0; i < MAX_ITENS; i++) {
            users.add(new User().setId(i).setNome(String.format("User %s", i)).setEmail(String.format("user%s@gmail.com", i)));
        }
        return users;
    }

    public List<User> findByOffset(int offset, int limit) {
        final List<User> users = new ArrayList<>(limit);
        for (int i = offset+1; i <= limit; i++) {
            users.add(new User().setId(i).setNome(String.format("User %s", i)).setEmail(String.format("user%s@gmail.com", i)));
        }
        return users;
    }

    public int count() {
        return MAX_ITENS;
    }
}
