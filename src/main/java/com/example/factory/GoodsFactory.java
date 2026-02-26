package com.example.factory;

import com.example.DTO.Goods;
import com.example.goods.RegularGoods;
import com.example.goods.SaleGoods;
import com.example.goods.SpecialOfferGoods;

public class GoodsFactory {

    public static Goods create(String name, String type) {

        switch (type) {
            case "REG": return new RegularGoods(name);
            case "SAL": return new SaleGoods(name);
            case "SPO": return new SpecialOfferGoods(name);
            default:
                throw new IllegalArgumentException("Unknown goods type");
        }
    }
}
