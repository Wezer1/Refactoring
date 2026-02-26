package com.example.view;

import com.example.DTO.Customer;
import com.example.DTO.Item;

public interface IView {

    StringBuilder getHeader(Customer customer);

    StringBuilder getFooter(double totalAmount, int totalBonus);

    StringBuilder getItemString(Item item,
                                double baseAmount,
                                double discount,
                                double finalAmount,
                                int bonus);
}

