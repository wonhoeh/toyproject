package toyproject.shop.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import toyproject.shop.domain.CategoryItem;
import toyproject.shop.exception.StockNotEnoughException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();


    //==비즈니스 로직==//
    public void setItem(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        if(quantity < 0) {
            throw new StockNotEnoughException("제품이 부족합니다.");
        }
        this.stockQuantity -= quantity;
    }

    //===set 메소드===//
}
