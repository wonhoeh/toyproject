package toyproject.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shop.domain.item.Item;
import toyproject.shop.exception.DataNotFoundException;
import toyproject.shop.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        if(findItem.isPresent()) {
            //findItem.get().changeItem(name, price, stockQuantity);
            Item oldItem = findItem.get();
            oldItem.setItem(name, price, stockQuantity);
        } else {
            throw new DataNotFoundException("존재하지 않는 상품입니다.");
        }
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        if(findItem.isPresent()) {
            return findItem.get();
        } else {
            throw new DataNotFoundException("존재하지 않는 상품입니다.");
        }
    }

}
