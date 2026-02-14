package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillTest {

    @Test
    void testRegularNoDiscount() { // кейс 1 (REGULAR 1)
        Customer c = new Customer("Alice", 0);
        Goods g = new Goods("Cola", Goods.REGULAR);
        Item i = new Item(g, 1, 100);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        String result = bill.statement();

        assertTrue(result.contains("Сумма счета составляет 100.0"));
        assertTrue(result.contains("Вы заработали 5 бонусных баллов"));
    }

    @Test
    void testRegularWithDiscountAndBonusUse() { // кейс 3 (REGULAR 6)
        Customer c = new Customer("Bob", 10);
        Goods g = new Goods("Cola", Goods.REGULAR);
        Item i = new Item(g, 6, 100); // >5, скидка + использование бонуса

        Bill bill = new Bill(c);
        bill.addGoods(i);
        String result = bill.statement();

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSaleDiscountAndBonus() {
        Customer c = new Customer("Charlie", 0);
        Goods g = new Goods("Pepsi", Goods.SALE);
        Item i = new Item(g, 4, 50);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        String result = bill.statement();

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSpecialOfferDiscountAndBonus() {
        Customer c = new Customer("Dana", 20);
        Goods g = new Goods("Fanta", Goods.SPECIAL_OFFER);
        Item i = new Item(g, 11, 30);
        Item j = new Item(g, 2, 30);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        bill.addGoods(j);
        String result = bill.statement();

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testMixedItems() {
        Customer c = new Customer("Eve", 15);
        Goods g1 = new Goods("Cola", Goods.REGULAR);
        Goods g2 = new Goods("Pepsi", Goods.SALE);
        Goods g3 = new Goods("Fanta", Goods.SPECIAL_OFFER);

        Bill bill = new Bill(c);
        bill.addGoods(new Item(g1, 3, 100));
        bill.addGoods(new Item(g2, 4, 50));
        bill.addGoods(new Item(g3, 2, 30));

        String result = bill.statement();
        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }


    @Test
    void testRegularWithDiscountOnly() {
        Customer c = new Customer("Frank", 0);
        Goods g = new Goods("Cola", Goods.REGULAR);
        Item i = new Item(g, 3, 100);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        String result = bill.statement();

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSaleWithBonusOnly() {
        Customer c = new Customer("Grace", 0);
        Goods g = new Goods("Pepsi", Goods.SALE);
        Item i = new Item(g, 2, 50);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        String result = bill.statement();

        assertTrue(result.contains("Сумма счета составляет"));
        assertTrue(result.contains("Вы заработали"));
    }

    @Test
    void testSpecialOfferNoDiscountNoBonus() {
        Customer c = new Customer("Hank", 0);
        Goods g = new Goods("Fanta", Goods.SPECIAL_OFFER);
        Item i = new Item(g, 1, 30);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        String result = bill.statement();

        assertTrue(result.contains("Сумма счета составляет 30.0"));
        assertTrue(result.contains("Вы заработали 0 бонусных баллов"));
    }

    @Test
    void testRegularOneItemBoundary() {
        Customer c = new Customer("Ivy", 5);
        Goods g = new Goods("Cola", Goods.REGULAR);
        Item i = new Item(g, 1, 50);

        Bill bill = new Bill(c);
        bill.addGoods(i);
        String result = bill.statement();

        assertTrue(result.matches("(?s).*Сумма счета составляет 50\\.0.*Вы заработали 2 бонусных баллов.*"));
    }
}

