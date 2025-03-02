package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * FAST (시스템에서 보여지는 값) : 빠른 배송 (사용자에게 보여지는 값)
 * NORMAL : 일반 배송
 * SLOW : 느린 배송
 * */
@Data
@AllArgsConstructor
public class DeliveryCode {

    private String code; // 시스템에서 보여지는 값 (FAST, NORMAL 등)
    private String displayName; // 사용자에게 보여지는 값 (빠른배송, 일반배송 등)
}
