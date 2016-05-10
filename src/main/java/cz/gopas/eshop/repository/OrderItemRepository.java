package cz.gopas.eshop.repository;

import org.springframework.data.jpa.repository.*;
import cz.gopas.eshop.entity.*;

public interface OrderItemRepository extends JpaRepository<OrderedItem, Integer> {

}
