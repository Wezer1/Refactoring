package com.example.formatFile;

import com.example.DTO.*;
import com.example.factory.GoodsFactory;

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
        } while (line.startsWith("#"));

        String[] data =
                line.split(":")[1].trim().split("\\s+");

        return GoodsFactory.create(data[0], data[1]);
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

            if (line == null) {
                return null;
            }

            line = line.trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            if (!line.contains(":")) {
                continue;
            }

            String right = line.split(":")[1].trim();

            if (right.matches("\\d+.*")) {
                break;
            }
        }

        String[] data =
                line.split(":")[1].trim().split("\\s+");

        int gid = Integer.parseInt(data[0]);
        double price = Double.parseDouble(data[1]);
        int qty = Integer.parseInt(data[2]);

        return new Item(goods[gid - 1], qty, price);
    }
}