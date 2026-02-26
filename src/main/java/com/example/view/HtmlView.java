package com.example.view;

import com.example.Customer;
import com.example.Item;

public class HtmlView implements IView {

    @Override
    public StringBuilder getHeader(Customer customer) {
        return new StringBuilder()
                .append("<h1>Счет для ").append(customer.getName()).append("</h1>")
                .append("<table border='1'>")
                .append("<tr>")
                .append("<th>Название</th>")
                .append("<th>Цена</th>")
                .append("<th>Кол-во</th>")
                .append("<th>Стоимость</th>")
                .append("<th>Скидка</th>")
                .append("<th>Сумма</th>")
                .append("<th>Бонус</th>")
                .append("</tr>");
    }

    @Override
    public StringBuilder getFooter(double totalAmount, int totalBonus) {
        return new StringBuilder()
                .append("</table>")
                .append("<p>Сумма счета: ").append(totalAmount).append("</p>")
                .append("<p>Заработано бонусов: ").append(totalBonus).append("</p>");
    }

    @Override
    public StringBuilder getItemString(Item item,
                                       double baseAmount,
                                       double discount,
                                       double finalAmount,
                                       int bonus) {

        return new StringBuilder()
                .append("<tr>")
                .append("<td>").append(item.getGoods().getTitle()).append("</td>")
                .append("<td>").append(item.getPrice()).append("</td>")
                .append("<td>").append(item.getQuantity()).append("</td>")
                .append("<td>").append(baseAmount).append("</td>")
                .append("<td>").append(discount).append("</td>")
                .append("<td>").append(finalAmount).append("</td>")
                .append("<td>").append(bonus).append("</td>")
                .append("</tr>");
    }
}
