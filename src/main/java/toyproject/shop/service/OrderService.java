package toyproject.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shop.domain.*;
import toyproject.shop.domain.item.Item;
import toyproject.shop.exception.DataNotFoundException;
import toyproject.shop.repository.ItemRepository;
import toyproject.shop.repository.MemberRepository;
import toyproject.shop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member;
        Optional<Member> findMember = memberRepository.findById(memberId);
        if(findMember.isPresent()) {
            member = findMember.get();
        } else {
            throw new DataNotFoundException("존재하지 않는 회원입니다.");
        }

        Item item;
        Optional<Item> findItem = itemRepository.findById(itemId);
        if(findItem.isPresent()) {
            item = findItem.get();
        } else {
            throw new DataNotFoundException("존재하지 않는 상품입니다.");
        }

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.createAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if(findOrder.isPresent()) {
            findOrder.get().cancel();
        } else {
            throw new DataNotFoundException("존재하지 않는 주문입니다.");
        }
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findOne(Long orderId) {
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if(findOrder.isPresent()) {
            return findOrder.get();
        } else {
            throw new DataNotFoundException("존재하지 않는 주문입니다.");
        }
    }

    /* 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
    */
}
