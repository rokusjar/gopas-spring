package cz.gopas.eshop.controller;

import java.util.*;
import java.util.stream.*;
import javax.validation.*;
import org.dozer.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;
import cz.gopas.eshop.dto.*;
import cz.gopas.eshop.entity.*;
import cz.gopas.eshop.service.*;

@Controller
public class IndexController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional //pred zahajenim metody vznikne transakce a cela metoda probehne v transkaci
    @RequestMapping("/")
    public String index(Model model) {

        List<Item> items = itemService.getItems();
        model.addAttribute("items", items);
        model.addAttribute("itemsDto", items.stream()
                .map(e -> mapper.map(e, ItemDto.class))  //tady se vynuti select
                .collect(Collectors.toList()));

        return "index";
    }


    @ModelAttribute
    public Item construct() {
        return new Item();
    }


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form() {
        return "item-form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute @Valid Item item, BindingResult bindingResult,
        RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return index(model);
        }

        itemService.save(item);
        redirectAttributes.addFlashAttribute("succes", true);
        return "redirect:/";
    }


}
