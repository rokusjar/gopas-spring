package cz.gopas.eshop.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import cz.gopas.eshop.entity.*;
import cz.gopas.eshop.repository.*;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getItems(){
        //return itemRepository.findAll();
        return itemRepository.findAllFetch();
    }

    public Item getItem(int id){
        return itemRepository.findOne(id);
    }

    @Cacheable("items")
    public List<Item> getItemsByName(String name){
        return itemRepository.findByNameContaining(name);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Scheduled(fixedDelay = 5000) //bude volana automaticky kazdych 5 sekund
    @CacheEvict(value = "items", allEntries = true) //ze metoda promaze kes
    public void deleteItemsCache(){}
}
