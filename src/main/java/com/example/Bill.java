package com.example;

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

    public String statement() {
        double totalAmount = 0;
        int totalBonus = 0;

        StringBuilder result = getHeader();

        for (Item item : items) {
            double itemSum = getSum(item);

            double[] discountData = getDiscount(item, customer);
            double discountAmount = discountData[0];
            double usedBonus = discountData[1];
            int bonusEarned = (int) discountData[2];

            double finalAmount = itemSum - discountAmount - usedBonus;

            result.append(formatItemLine(item, itemSum, discountAmount, finalAmount, bonusEarned));

            totalAmount += finalAmount;
            totalBonus += bonusEarned;
        }

        result.append(getFooter(totalAmount, totalBonus));

        customer.receiveBonus(totalBonus);

        return result.toString();
    }

    public StringBuilder getHeader() {
        return new StringBuilder()
                .append("Счет для ").append(customer.getName()).append("\n")
                .append("\tНазвание\tЦена\tКол-во\tСтоимость\tСкидка\tСумма\tБонус\n");
    }

    public StringBuilder getFooter(double totalAmount, int totalBonus) {
        return new StringBuilder()
                .append("\nСумма счета составляет ").append(totalAmount).append("\n")
                .append("Вы заработали ").append(totalBonus).append(" бонусных баллов\n");
    }

    public StringBuilder formatItemLine(Item item, double baseAmount, double discount, double finalAmount, int bonus) {
        return new StringBuilder(String.format(
                "\t%-10s\t%6.1f\t%6d\t%8.1f\t%6.1f\t%8.1f\t%5d\n",
                item.getGoods().getTitle(),
                item.getPrice(),
                item.getQuantity(),
                baseAmount,
                discount,
                finalAmount,
                bonus
        ));
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
