package cz.gopas.eshop.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "ordereditem")
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordereditemid")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne()
    @JoinColumn(name = "user_order_id")
    private UserOrder userOrder;

    public OrderedItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}
