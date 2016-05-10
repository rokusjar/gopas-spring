package cz.gopas.eshop.repository;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import cz.gopas.eshop.entity.*;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByNameContaining(String name);
}
