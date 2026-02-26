package com.example;

import com.example.DTO.BillSummary;
import com.example.DTO.ItemSummary;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private List<Item> items;
    private Customer customer;

    public Bill(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addGoods(Item item) {
        items.add(item);
    }

    public Customer getCustomer() {
        return customer;
    }

    public BillSummary process() {

        BillSummary summary = new BillSummary();

        for (Item item : items) {

            double itemSum = getSum(item);

            double[] discountData = getDiscount(item);
            double discountAmount = discountData[0];
            double usedBonus = discountData[1];
            int bonusEarned = (int) discountData[2];

            double finalAmount = itemSum - discountAmount - usedBonus;

            ItemSummary itemSummary = new ItemSummary(
                    item,
                    itemSum,
                    discountAmount,
                    usedBonus,
                    finalAmount,
                    bonusEarned
            );

            summary.addItem(itemSummary);
            summary.addToTotalAmount(finalAmount);
            summary.addToTotalBonus(bonusEarned);
        }

        customer.receiveBonus(summary.getTotalBonus());

        return summary;
    }

    private double getSum(Item item) {
        return item.getQuantity() * item.getPrice();
    }

    private double[] getDiscount(Item item) {
        double quantity = item.getQuantity();
        double price = item.getPrice();

        double[] base = item.getGoods().getBonus((int) quantity, price);
        double discountAmount = base[0];
        int bonusEarned = (int) base[1];

        double itemSum = quantity * price;
        double sumAfterDiscount = itemSum - discountAmount;

        double usedBonus = item.getGoods()
                .getUsedBonus((int) quantity, sumAfterDiscount, customer);

        return new double[]{discountAmount, usedBonus, bonusEarned};
    }
}