package toyproject.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shop.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
