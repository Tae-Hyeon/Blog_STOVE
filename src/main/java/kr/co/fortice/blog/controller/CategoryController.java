package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return PageList.POST_CREATE_PAGE.resource() + " :: #categories";
    }
}
