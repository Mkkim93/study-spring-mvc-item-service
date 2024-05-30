package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    // 상품 등록
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // 단일 상품 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    // 전체 상품 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 상품 수정
    public void update(Long itemId, Item updateItem) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateItem.getItemName());
        findItem.setPrice(updateItem.getPrice());
        findItem.setQuantity(updateItem.getQuantity());
        findItem.setOpen(updateItem.getOpen());
        findItem.setRegions(updateItem.getRegions());
        findItem.setItemType(updateItem.getItemType());
        findItem.setDeliveryCode(updateItem.getDeliveryCode());
    }

    // 리스트 초기화
    public void clearStore() {
        store.clear();
    }
}
