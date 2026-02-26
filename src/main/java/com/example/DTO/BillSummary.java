package com.example.DTO;

import java.util.ArrayList;
import java.util.List;

public class BillSummary {

    private List<ItemSummary> items = new ArrayList<>();
    private double totalAmount;
    private int totalBonus;

    public void addItem(ItemSummary itemSummary) {
        items.add(itemSummary);
    }

    public List<ItemSummary> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addToTotalAmount(double amount) {
        this.totalAmount += amount;
    }

    public int getTotalBonus() {
        return totalBonus;
    }

    public void addToTotalBonus(int bonus) {
        this.totalBonus += bonus;
    }
}
