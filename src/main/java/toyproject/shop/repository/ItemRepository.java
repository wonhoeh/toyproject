package toyproject.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shop.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
