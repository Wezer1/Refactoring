package com.example.formatFile;

import com.example.DTO.Customer;
import com.example.DTO.Goods;
import com.example.DTO.Item;
import com.example.bonusStrategy.PercentFromItemSumBonusStrategy;
import com.example.discountStrategy.*;
import com.example.formatFile.IFileSource;

import java.io.BufferedReader;
import java.io.IOException;

public class YamlFileSource implements IFileSource {

    private BufferedReader reader;

    @Override
    public void setSource(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public Customer getCustomer() throws IOException {
        String line = reader.readLine();
        String name = line.split(":")[1].trim();

        line = reader.readLine();
        int bonus = Integer.parseInt(line.split(":")[1].trim());

        return new Customer(name, bonus);
    }

    @Override
    public int getGoodsCount() throws IOException {
        String line = reader.readLine();
        return Integer.parseInt(line.split(":")[1].trim());
    }

    @Override
    public Goods getNextGood() throws IOException {

        String line;
        do {
            line = reader.readLine();
            if (line == null) return null;
        } while (line.trim().isEmpty() || line.startsWith("#"));

        String[] data = line.split(":")[1].trim().split("\\s+");

        String title = data[0];
        String type = data[1].toUpperCase();

        // Создаём Goods напрямую с нужными стратегиями
        return switch (type) {
            case "REG" -> new Goods(
                    title,
                    new QuantityConditionalBonusStrategy(5, 5),
                    new QuantityThresholdPercentDiscountStrategy(2, 3)
            );
            case "SAL" -> new Goods(
                    title,
                    new PercentFromItemSumBonusStrategy(1),
                    new QuantityThresholdPercentDiscountStrategy(3, 1)
            );
            case "SPO" -> new Goods(
                    title,
                    new QuantityConditionalBonusStrategy(1, 0), // бонусов нет, можно списывать если >1
                    new QuantityThresholdPercentDiscountStrategy(10, 0.5)
            );
            default -> throw new IllegalArgumentException("Unknown goods type: " + type);
        };
    }

    @Override
    public int getItemsCount() throws IOException {
        String line = reader.readLine();
        return Integer.parseInt(line.split(":")[1].trim());
    }

    @Override
    public Item getNextItem(Goods[] goods) throws IOException {

        String line;

        while (true) {
            line = reader.readLine();
            if (line == null) return null;

            line = line.trim();
            if (line.isEmpty() || line.startsWith("#") || !line.contains(":")) {
                continue;
            }

            String right = line.split(":")[1].trim();
            if (right.matches("\\d+.*")) {
                break;
            }
        }

        String[] data = line.split(":")[1].trim().split("\\s+");

        int gid = Integer.parseInt(data[0]);
        double price = Double.parseDouble(data[1]);
        int qty = Integer.parseInt(data[2]);

        return new Item(goods[gid - 1], qty, price);
    }
}