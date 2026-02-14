package com.example.test;

import com.example.*;
import com.example.view.HtmlView;
import com.example.view.IView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProgramHtml {

    public static void main(String[] args) throws IOException {

        String filename = "BillInfo.yaml";

        if (args.length == 1) {
            filename = args[0];
        }

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line = reader.readLine();
        String[] result = line.split(":");
        String name = result[1].trim();

        line = reader.readLine();
        result = line.split(":");
        int bonus = Integer.parseInt(result[1].trim());

        Customer customer = new Customer(name, bonus);

        // ⬇ HTML-представление
        IView view = new HtmlView();
        Bill bill = new Bill(customer, view);

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

            String type = result[1].trim();

            switch (type) {
                case "REG":
                    g[i] = new RegularGoods(result[0]);
                    break;
                case "SAL":
                    g[i] = new SaleGoods(result[0]);
                    break;
                case "SPO":
                    g[i] = new SpecialOfferGoods(result[0]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown goods type");
            }
        }

        do {
            line = reader.readLine();
        } while (line.startsWith("#"));

        result = line.split(":");
        int itemsQty = Integer.parseInt(result[1].trim());

        for (int i = 0; i < itemsQty; i++) {

            do {
                line = reader.readLine();
            } while (line.startsWith("#"));

            result = line.split(":");
            result = result[1].trim().split("\\s+");

            int gid = Integer.parseInt(result[0].trim());
            double price = Double.parseDouble(result[1].trim());
            int qty = Integer.parseInt(result[2].trim());

            bill.addGoods(new Item(g[gid - 1], qty, price));
        }

        reader.close();

        // Генерируем HTML
        String htmlContent = "<!DOCTYPE html>\n<html>\n<head><meta charset='UTF-8'><title>Счет</title></head>\n<body>\n"
                + bill.statement()
                + "\n</body>\n</html>";

        // Записываем в файл
        try (FileWriter writer = new FileWriter("bill.html")) {
            writer.write(htmlContent);
        }

        System.out.println("HTML-счет успешно создан: bill.html");
    }
}
