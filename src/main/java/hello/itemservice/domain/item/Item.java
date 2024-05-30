package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * 1. 상품 도메인 개발
 */

@Data
public class Item {

    private Long id;
    private String itemName;  // 상품 이름
    private Integer price;    // 상품 가격
    private Integer quantity; // 상품 수량
    private int age;

    private Boolean open; // 판매 여부 참고: Boolean(객체형) getter 생성 시 'getOpen()' 으로 생성
//    private boolean closed; -> 참고 : boolean(기본형) getter 생성 시 'isClosed()' 으로 생성
    private List<String> regions; // 등록 지역
    private ItemType itemType; // 상품 종류 (ENUM)
    private String deliveryCode; // 배송 방식

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}