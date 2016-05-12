package cz.gopas.eshop.entity;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int id;

    @OneToMany(mappedBy = "item")
    private List<OrderedItem> orderedItems;

    @Size(min = 1, message = "Name cannot be empty!")
    private String name;

    @Min(value = 1, message = "Price cannot be empty!")
    private double price;

    public Item() {
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
