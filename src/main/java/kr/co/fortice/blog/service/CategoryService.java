package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.response.CategoryListResponse;
import kr.co.fortice.blog.entity.Category;
import kr.co.fortice.blog.repository.CategoryRepository;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryListResponse getCategories() {
        List<Category> categories = categoryRepository.findAllByBlogId(SessionUtil.getBlogId());
        return CategoryListResponse.of(categories);
    }
}
