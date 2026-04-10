package com.example.bonusStrategy;

import com.example.DTO.Customer;

public interface BonusStrategy {

    int calculateBonus(int quantity, double price, double itemSum, double billTotal);

    double useBonus(int quantity,
                    double sumAfterDiscount,
                    Customer customer);
}
