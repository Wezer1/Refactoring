package com.example.view;

import com.example.Customer;
import com.example.Item;

public class TxtView implements IView {

    @Override
    public StringBuilder getHeader(Customer customer) {
        return new StringBuilder()
                .append("Счет для ").append(customer.getName()).append("\n")
                .append("\tНазвание\tЦена\tКол-во\tСтоимость\tСкидка\tСумма\tБонус\n");
    }

    @Override
    public StringBuilder getFooter(double totalAmount, int totalBonus) {
        return new StringBuilder()
                .append("\nСумма счета составляет ").append(totalAmount).append("\n")
                .append("Вы заработали ").append(totalBonus).append(" бонусных баллов\n");
    }

    @Override
    public StringBuilder getItemString(Item item,
                                       double baseAmount,
                                       double discount,
                                       double finalAmount,
                                       int bonus) {

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
}
