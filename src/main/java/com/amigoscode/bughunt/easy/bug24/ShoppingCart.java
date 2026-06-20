package com.amigoscode.bughunt.easy.bug24;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<String> items = new ArrayList<>();
    private final String customer;

    public ShoppingCart(String customer) {
        this.customer = customer;
    }

    public void add(String item) {
        items.add(item);
    }

    public void removeAllContaining(String substring) {
        items.removeIf(item ->
                item.toLowerCase().contains(substring.toLowerCase()));
    }

    public int size() {
        return items.size();
    }

    public List<String> items() {
        return List.copyOf(items);
    }

    public String summary() {
        return customer + "'s cart: " + size() + " items";
    }
}
