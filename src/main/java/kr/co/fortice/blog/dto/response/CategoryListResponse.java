package kr.co.fortice.blog.dto.response;

import kr.co.fortice.blog.entity.Category;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class CategoryListResponse {
    Map<Integer, String> category;

    public CategoryListResponse(Map<Integer, String> category) {
        this.category = category;
    }

    public static CategoryListResponse of(List<Category> categories) {
        return new CategoryListResponse(
                categories
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Category::getId,
                                        Category::getTitle
                                )
                        )
        );
    }
}
