package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("상품 저장 기능 테스트")
    void save() {
        // 저장할 상품을 먼저 선언하고 item 객체를 생성한다.
        Item item = new Item("치토스", 20000, 5);

        // 저장 기능을 사용한다 (item) 을 저장
        Item firstItem = itemRepository.save(item);

        // 저장된 item 이 실제 저장할 상품이 맞는지 출력한다.
        System.out.println("item = " + item);

        // 저장된 아이템이 실제 생성한 아이템과 같은지 확인
        assertThat(item).isEqualTo(firstItem);

    }

    @Test
    @DisplayName("전체 상품 조회 테스트")
    void findAll() {
        // given (item1, 2가 존재한다.)
        Item item1 = new Item("썬칩", 1500, 2);
        Item item2 = new Item("바나나킥", 1200, 1);
        Item item3 = new Item("화이트하임", 1400, 3);

        // when item1, 2의 데이터를 저장한다.
        itemRepository.save(item1);
        itemRepository.save(item2);
//        itemRepository.save(item3);

        // 저장한 데이터를 리스트로 변환하고 items 를 모두 찾는다.
        List<Item> items = itemRepository.findAll();

        // 저장된 list(items) 의 갯수가 2인지 확인힌다. (item1, item2 가 저장되어있으므로 2면 true)
        assertThat(items.size()).isEqualTo(2);

        // items 에 저장된 데이터가 item1 과 item2 가 맞는지 확인한다.
        assertThat(items).contains(item1, item2);
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void update() {
        // 1. 먼저 저장할 item 을 생성한다.
        Item firstItem = new Item("바밤바", 2000, 5);

        // 2. 기존 상품의 데이터와 id를 각각 별도로 저장한다.
        Item saveItem = itemRepository.save(firstItem); // 상품 저장 : result
        Long itemId = saveItem.getId(); // id 저장 : itemId

        // 3. 기존 상품에서 수정할 데이터를 생성한다 : updateItem
        Item updateItem = new Item("스크류바", 1500, 2);
        itemRepository.update(itemId, updateItem); // 기존 상품(itemId -> updateItem) 으로 변경하는 메서드를 실행한다.

        // 4. 업데이트된 상품을 다시 조회하여 확인한다.
        // (즉 현재 스크류바로 만들어진 상품목록이 updateItem 과 findItem 두개가 존재하는것이고, 아래의 isEqualsTo 를 통해 두 상품의 일치성을 확인한다.
        Item findItem = itemRepository.findById(itemId);

        // 5. 업데이트된 updateItem 이 생성한 데이터와 일치하는지 확인한다. (findItem == updateItem)
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}