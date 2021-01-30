package ltd.ygao.search.controller;

import ltd.ygao.search.service.MallSearchService;
import ltd.ygao.search.vo.SearchParam;
import ltd.ygao.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private MallSearchService mallSearchService;
    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model) {
        SearchResult res= mallSearchService.search(param);
        model.addAttribute("result",res);
        return "list";
    }
}
