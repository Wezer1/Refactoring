package com.example;

import com.example.DTO.BillSummary;
import com.example.DTO.Customer;
import com.example.DTO.Item;
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

        double billTotal = calculateBillTotal();

        for (Item item : items) {

            double itemSum = getSum(item);

            double discount = item.getGoods()
                    .getDiscountStrategy()
                    .calculateDiscount(
                            item.getQuantity(),
                            item.getPrice(),
                            itemSum,
                            billTotal
                    );

            double sumAfterDiscount = itemSum - discount;

            int bonusEarned = item.getGoods()
                    .getBonusStrategy()
                    .calculateBonus(
                            item.getQuantity(),
                            item.getPrice(),
                            itemSum,
                            billTotal
                    );

            double usedBonus = item.getGoods()
                    .getBonusStrategy()
                    .useBonus(
                            item.getQuantity(),
                            sumAfterDiscount,
                            customer
                    );

            double finalAmount = sumAfterDiscount - usedBonus;

            ItemSummary itemSummary = new ItemSummary(
                    item,
                    itemSum,
                    discount,
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

    private double calculateBillTotal() {
        return items.stream()
                .mapToDouble(this::getSum)
                .sum();
    }

    private double getSum(Item item) {
        return item.getQuantity() * item.getPrice();
    }
}