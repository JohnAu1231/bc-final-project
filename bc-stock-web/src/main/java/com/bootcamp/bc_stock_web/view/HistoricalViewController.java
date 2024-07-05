package com.bootcamp.bc_stock_web.view;


  
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.bc_stock_web.controller.impl.StockPriceController;
import com.bootcamp.bc_stock_web.model.StockPrice;

@Controller
public class HistoricalViewController {

    @GetMapping("/historical")
    public String historical(Model model) {
        return "historical";
    }
    
}
