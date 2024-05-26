package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * TEST 용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 1000 ,10));
        itemRepository.save(new Item("itemB", 2000 ,20));
        itemRepository.save(new Item("itemC", 3000 ,30));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }



    // 임의의 객체 (Item) 등과 같은 경우는 @ModelAttribute 생략 가능
    // @PostMapping("/add")
    public String addItemV4(Item item) {

        itemRepository.save(item);

        return "/basic/item"; // 해당 페이지 새로고침 : POST
    }

    // 상품 등록 후 상세페이지에서 새로고침을 하여도 데이터가 중복되어 증가하지 않는다.
    // @PostMapping("/add")
    public String addItemV5(Item item, Model model) {

        itemRepository.save(item);


        return "redirect:/basic/items/" + item.getId(); // 해당 페이지에서 새로고침 : GET
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}"; // 해당 페이지에서 새로고침 : GET
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    // 상품 수정 (저장) 기존 item: Long itemId -> 새로운 item: Item item
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {

        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

























    // @PostMapping("/add")
    // @ModelAttribute 사용 전
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity, Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    // @ModelAttribute 사용 후
    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);
        return "basic/item";

        /**@ModelAttribute 효과 아래 코드 생략 가능*/
        // Item item = new Item();
        // item.setItemName(itemName);
        // item.setPrice(price);
        // item.setQuantity(quantity);
        // model.addAttribute("item", item); -> 메서드 선언 시점에 @ModelAttribute() 내부에 해당 객체명 입력 시 생략 가능
    }

    // @ModelAttribute 만 선언 하였을 떄 ("item")가 없을 떄 -> 객체로 선언한 Item -> item 으로 변경되어 model.Attribute 에 담긴다 즉, 객체의 첫글자가 소문자로 바뀜
    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {

        itemRepository.save(item);
        return "basic/item";
    }
}
