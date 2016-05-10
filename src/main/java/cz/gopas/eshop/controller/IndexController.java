package cz.gopas.eshop.controller;

import java.util.stream.*;
import org.dozer.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import cz.gopas.eshop.dto.*;
import cz.gopas.eshop.service.*;

@Controller
public class IndexController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("items", itemService.getItems());

        model.addAttribute("itemsDto", itemService.getItems().stream()
                .map(e -> mapper.map(e, ItemDto.class))
                .collect(Collectors.toList()));

        return "index";
    }
}
