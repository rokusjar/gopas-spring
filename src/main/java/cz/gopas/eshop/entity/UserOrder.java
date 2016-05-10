package cz.gopas.eshop.entity;

import java.util.*;
import javax.persistence.*;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_order_id")
    private int id;

    @OneToMany(mappedBy = "userOrder")
    private List<OrderedItem> orderedItems;

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
