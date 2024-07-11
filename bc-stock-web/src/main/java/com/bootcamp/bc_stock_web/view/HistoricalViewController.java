package com.bootcamp.bc_stock_web.view;


  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoricalViewController {

    @GetMapping("/historical")
    public String historical(Model model) {
        return "historical";
    }
    
}
