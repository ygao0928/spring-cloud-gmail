package ltd.ygao.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName SearchController.java
 * @date 2021/1/25 0:09
 * @Description
 * @Content:
 */
@Controller
public class SearchController {
    @GetMapping("/list.html")
    public String listPage(){
        return "list";
    }
}
