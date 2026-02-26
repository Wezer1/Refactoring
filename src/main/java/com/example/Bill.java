package com.example;

import com.example.view.IView;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private List<Item> items;
    private Customer customer;
    private IView view;

    public Bill(Customer customer, IView view) {
        this.customer = customer;
        this.view = view;
        this.items = new ArrayList<>();
    }

    public void addGoods(Item item) {
        items.add(item);
    }

    public String statement() {
        double totalAmount = 0;
        int totalBonus = 0;

        StringBuilder result = view.getHeader(customer);

        for (Item item : items) {

            double itemSum = getSum(item);

            double[] discountData = getDiscount(item, customer);
            double discountAmount = discountData[0];
            double usedBonus = discountData[1];
            int bonusEarned = (int) discountData[2];

            double finalAmount = itemSum - discountAmount - usedBonus;

            result.append(view.getItemString(
                    item,
                    itemSum,
                    discountAmount,
                    finalAmount,
                    bonusEarned
            ));

            totalAmount += finalAmount;
            totalBonus += bonusEarned;
        }

        result.append(view.getFooter(totalAmount, totalBonus));

        customer.receiveBonus(totalBonus);

        return result.toString();
    }

    private double getSum(Item item) {
        return item.getQuantity() * item.getPrice();
    }

    private double[] getDiscount(Item item, Customer customer) {
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
