package com.example;

import com.example.DTO.Customer;
import com.example.DTO.Goods;
import com.example.DTO.Item;
import com.example.goods.RegularGoods;
import com.example.goods.SaleGoods;
import com.example.goods.SpecialOfferGoods;
import com.example.view.TxtView;
import org.junit.jupiter.api.Test;
import com.example.generator.BillGenerator;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillTest {

    private String generateResult(Bill bill) {
        BillGenerator generator = new BillGenerator(bill, new TxtView());
        return generator.generate();
    }

    @Test
    void testRegularNoDiscount() {
        Customer c = new Customer("Alice", 0);
        Goods g = new RegularGoods("Cola");
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
        Goods g = new RegularGoods("Cola");
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
        Goods g = new SaleGoods("Pepsi");
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
        Goods g = new SpecialOfferGoods("Fanta");
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
        Goods g1 = new RegularGoods("Cola");
        Goods g2 = new SaleGoods("Pepsi");
        Goods g3 = new SpecialOfferGoods("Fanta");

        Bill bill = new Bill(c);
        bill.addGoods(new Item(g1, 3, 100));
        bill.addGoods(new Item(g2, 4, 50));
        bill.addGoods(new Item(g3, 2, 30));

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testRegularWithDiscountOnly() {
        Customer c = new Customer("Frank", 0);
        Goods g = new RegularGoods("Cola");
        Item i = new Item(g, 3, 100);

        Bill bill = new Bill(c);
        bill.addGoods(i);

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSaleWithBonusOnly() {
        Customer c = new Customer("Grace", 0);
        Goods g = new SaleGoods("Pepsi");
        Item i = new Item(g, 2, 50);

        Bill bill = new Bill(c);
        bill.addGoods(i);

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSpecialOfferNoDiscountNoBonus() {
        Customer c = new Customer("Hank", 0);
        Goods g = new SpecialOfferGoods("Fanta");
        Item i = new Item(g, 1, 30);

        Bill bill = new Bill(c);
        bill.addGoods(i);

        String result = generateResult(bill);

        assertTrue(result.contains("Сумма счета составляет 30.0"));
        assertTrue(result.contains("Вы заработали 0 бонусных баллов"));
    }

    @Test
    void testRegularOneItemBoundary() {
        Customer c = new Customer("Ivy", 5);
        Goods g = new RegularGoods("Cola");
        Item i = new Item(g, 1, 50);

        Bill bill = new Bill(c);
        bill.addGoods(i);

        String result = generateResult(bill);

        assertTrue(result.matches(
                "(?s).*Сумма счета составляет 50\\.0.*Вы заработали 2 бонусных баллов.*"
        ));
    }

    @Test
    void testParsing() throws Exception {

        String yaml =
                "Customer: Alice\n" +
                        "Bonus: 0\n" +
                        "Goods: 1\n" +
                        "Good: Cola REG\n" +
                        "Items: 1\n" +
                        "Item: 1 100 1\n";

        BufferedReader reader =
                new BufferedReader(new StringReader(yaml));

        Bill bill = Main.createBill(reader);

        BillGenerator generator =
                new BillGenerator(bill, new TxtView());

        String result = generator.generate();

        assertTrue(result.contains("Alice"));
    }
}