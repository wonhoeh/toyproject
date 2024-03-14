package toyproject.shop.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shop.domain.Address;
import toyproject.shop.domain.Member;
import toyproject.shop.domain.Order;
import toyproject.shop.domain.item.Album;
import toyproject.shop.domain.item.Book;
import toyproject.shop.domain.item.Item;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired EntityManager em;

    @Test
    public void 주문() throws Exception {
        //given
        Member member = createMember("히사이시조", 40, new Address("일본", "신주쿠", "123-456"));
        Book book = createBook("부의 추월차선", 30000, 1, "김몰라", "123-456");

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 20);
        Order findOrder = orderService.findOne(orderId);
        //then
        assertThat(orderId).isEqualTo(findOrder.getId());
    }

    private Member createMember(String name, int age, Address address) {
        Member member = new Member();
        member.setName(name);
        member.setAge(age);
        member.setAddress(address);
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int quantity, String author, String isbn) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        book.setAuthor(author);
        book.setIsbn(isbn);
        em.persist(book);
        return book;
    }


}