package com.example;

import com.example.DTO.Customer;
import com.example.DTO.Goods;
import com.example.DTO.Item;
import com.example.bonusStrategy.PercentFromItemSumBonusStrategy;
import com.example.discountStrategy.*;
import com.example.view.TxtView;
import org.junit.jupiter.api.Test;
import com.example.generator.BillGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillTest {

    private String generateResult(Bill bill) {
        BillGenerator generator = new BillGenerator(bill, new TxtView());
        return generator.generate();
    }

    @Test
    void testRegularNoDiscount() {
        Customer c = new Customer("Alice", 0);
        Goods g = new Goods(
                "Cola",
                new QuantityConditionalBonusStrategy(5, 5), // 5% бонусов, списание при >5
                new QuantityThresholdPercentDiscountStrategy(2, 3) // 3% скидки, если >2
        );
        Item i = new Item(g, 1, 100);

        Bill bill = new Bill(c);
        bill.addGoods(i);

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет 100.0"));
        assertTrue(result.contains("Вы заработали 5 бонусных баллов"));
    }

    @Test
    void testRegularWithDiscountAndBonusUse() {
        Customer c = new Customer("Bob", 10);
        Goods g = new Goods(
                "Cola",
                new QuantityConditionalBonusStrategy(5, 5),
                new QuantityThresholdPercentDiscountStrategy(2, 3)
        );
        Item i = new Item(g, 6, 100);

        Bill bill = new Bill(c);
        bill.addGoods(i);

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSaleDiscountAndBonus() {
        Customer c = new Customer("Charlie", 0);
        Goods g = new Goods(
                "Pepsi",
                new PercentFromItemSumBonusStrategy(1), // 1% бонус
                new QuantityThresholdPercentDiscountStrategy(3, 1) // 1% скидка при >3
        );
        Item i = new Item(g, 4, 50);

        Bill bill = new Bill(c);
        bill.addGoods(i);

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSpecialOfferDiscountAndBonus() {
        Customer c = new Customer("Dana", 20);
        Goods g = new Goods(
                "Fanta",
                new QuantityConditionalBonusStrategy(1, 0), // бонусов нет, можно списывать если >1
                new QuantityThresholdPercentDiscountStrategy(10, 0.5) // 0.5% скидка если >10
        );
        Item i = new Item(g, 11, 30);
        Item j = new Item(g, 2, 30);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        bill.addGoods(j);

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testMixedItems() {
        Customer c = new Customer("Eve", 15);

        Goods g1 = new Goods(
                "Cola",
                new QuantityConditionalBonusStrategy(5, 5),
                new QuantityThresholdPercentDiscountStrategy(2, 3)
        );
        Goods g2 = new Goods(
                "Pepsi",
                new PercentFromItemSumBonusStrategy(1),
                new QuantityThresholdPercentDiscountStrategy(3, 1)
        );
        Goods g3 = new Goods(
                "Fanta",
                new QuantityConditionalBonusStrategy(1, 0),
                new QuantityThresholdPercentDiscountStrategy(10, 0.5)
        );

        Bill bill = new Bill(c);
        bill.addGoods(new Item(g1, 3, 100));
        bill.addGoods(new Item(g2, 4, 50));
        bill.addGoods(new Item(g3, 2, 30));

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void shouldApplyDiscountIfBillTotalMoreThanThreshold() {

        DiscountStrategy discount =
                new DiscountIfBillTotalMoreThanStrategy(1000, 10);

        double result = discount.calculateDiscount(
                2,
                600,
                1200,
                1200
        );

        assertEquals(120, result);
    }
}