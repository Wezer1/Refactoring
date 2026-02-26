package com.example;

import com.example.DTO.Customer;
import com.example.DTO.Goods;
import com.example.DTO.Item;
import com.example.goods.RegularGoods;
import com.example.goods.SaleGoods;
import com.example.goods.SpecialOfferGoods;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static Bill createBill(BufferedReader reader) throws IOException {

        String line = reader.readLine();
        String[] result = line.split(":");
        String name = result[1].trim();

        line = reader.readLine();
        result = line.split(":");
        int bonus = Integer.parseInt(result[1].trim());

        Customer customer = new Customer(name, bonus);
        Bill bill = new Bill(customer);

        line = reader.readLine();
        result = line.split(":");
        int goodsQty = Integer.parseInt(result[1].trim());

        Goods[] g = new Goods[goodsQty];

        for (int i = 0; i < g.length; i++) {

            do {
                line = reader.readLine();
            } while (line.startsWith("#"));

            result = line.split(":");
            result = result[1].trim().split("\\s+");

            String type = result[1];

            switch (type) {
                case "REG": g[i] = new RegularGoods(result[0]); break;
                case "SAL": g[i] = new SaleGoods(result[0]); break;
                case "SPO": g[i] = new SpecialOfferGoods(result[0]); break;
            }
        }

        line = reader.readLine();
        result = line.split(":");
        int itemsQty = Integer.parseInt(result[1].trim());

        for (int i = 0; i < itemsQty; i++) {

            line = reader.readLine();
            result = line.split(":");
            result = result[1].trim().split("\\s+");

            int gid = Integer.parseInt(result[0]);
            double price = Double.parseDouble(result[1]);
            int qty = Integer.parseInt(result[2]);

            bill.addGoods(new Item(g[gid - 1], qty, price));
        }

        return bill;
    }
}
