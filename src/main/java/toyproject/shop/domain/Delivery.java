package toyproject.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public void changeOrder(Order order) {
        this.order = order;
    }

    public void createAddress(Address address) {
        this.address = address;
    }
}
