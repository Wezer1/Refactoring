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

        String result = "Счет для " + customer.getName() + "\n";
        result += "\tНазвание\tЦена\tКол-во\tСтоимость\tСкидка\tСумма\tБонус\n";
        for (Item each : items) {

            double thisAmount = 0;
            double discount = 0;
            int bonus = 0;

            double baseAmount = each.getQuantity() * each.getPrice();
            switch (each.getGoods().getPriceCode()) {

                case Goods.REGULAR:
                    if (each.getQuantity() > 2) {
                        discount = baseAmount * 0.03;
                    }
                    bonus = (int)(baseAmount * 0.05);
                    break;

                case Goods.SPECIAL_OFFER:
                    if (each.getQuantity() > 10) {
                        discount = baseAmount * 0.005;
                    }
                    break;

                case Goods.SALE:
                    if (each.getQuantity() > 3) {
                        discount = baseAmount * 0.01;
                    }
                    bonus = (int)(baseAmount * 0.01);
                    break;
            }
            if (each.getGoods().getPriceCode() == Goods.REGULAR
                    && each.getQuantity() > 5) {

                discount += customer.useBonus((int) baseAmount);
            }

            if (each.getGoods().getPriceCode() == Goods.SPECIAL_OFFER
                    && each.getQuantity() > 1) {

                discount = customer.useBonus((int) baseAmount);
            }
            thisAmount = baseAmount - discount;

            result += "\t" + each.getGoods().getTitle()
                    + "\t" + each.getPrice()
                    + "\t" + each.getQuantity()
                    + "\t" + baseAmount
                    + "\t" + discount
                    + "\t" + thisAmount
                    + "\t" + bonus + "\n";

            totalAmount += thisAmount;
            totalBonus += bonus;
        }
        result += "\nСумма счета составляет " + totalAmount + "\n";
        result += "Вы заработали " + totalBonus + " бонусных баллов\n";

        customer.receiveBonus(totalBonus);

        return result;
    }
}


