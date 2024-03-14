package toyproject.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import toyproject.shop.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class CategoryItem {

    @Id @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
