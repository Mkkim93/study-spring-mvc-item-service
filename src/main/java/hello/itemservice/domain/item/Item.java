package hello.itemservice.domain.item;

import lombok.Data;
/**
 * 1. 상품 도메인 개발
 */

@Data
public class Item {

    private Long id;
    private String itemName;  // 상품 이름
    private Integer price;    // 상품 가격
    private Integer quantity; // 상품 수량

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;

    }
}