package cz.gopas.eshop.controller;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import org.dozer.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import cz.gopas.eshop.dto.*;
import cz.gopas.eshop.entity.*;
import cz.gopas.eshop.exception.*;
import cz.gopas.eshop.service.*;

@Transactional
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired //muze byt na atributech, konstruktorech a geterech/setrech
    private ItemService itemService;

    @Autowired
    private DozerBeanMapper mapper;

    // Bude zavolana pokud URL /item bude obsahovat parametr sortBy
    @RequestMapping(params = "sortBy")
    public List<ItemDto> itemsSortedBy(@RequestParam String sortBy) {

        //TODO
        return null;
    }

    @RequestMapping
    public List<ItemDto> items(){

        List<Item> items = itemService.getItems();
        List<ItemDto> itemsDto = new ArrayList<>();
        for(Item item : items){
            itemsDto.add(mapper.map(item, ItemDto.class));
        }

        return itemsDto;
//        //funguje stejne
//        return itemService.getItems().stream()
//                .map(e -> mapper.map(e, ItemDto.class))
//                .collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public ItemDto item(@PathVariable int id){

        return mapper.map(itemService.getItem(id), ItemDto.class);
    }

    @ExceptionHandler
    public void handleINFException(ItemNotFoundException ex, HttpServletResponse response)
        throws IOException{

        response.sendError(HttpServletResponse.SC_NOT_FOUND);

    }

    @RequestMapping("/search/{name}")
    public List<ItemDto> itemsByName(@PathVariable String name){

        List<Item> items = itemService.getItemsByName(name);
        List<ItemDto> itemsDto = new ArrayList<>();
        for(Item item : items) {
            itemsDto.add(mapper.map(item, ItemDto.class));
        }
        return itemsDto;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ItemDto save(@RequestBody ItemDto itemDto){
        itemDto.setId(0);
        Item item = mapper.map(itemDto, Item.class);
        Item savedItem = itemService.save(item);
        return mapper.map(savedItem, ItemDto.class);
    }
}
