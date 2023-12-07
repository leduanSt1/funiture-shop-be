package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.DTO.CategoryDTO;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Room;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Iterable<Category> getCategoriesByRoom(Integer roomId);
    public Iterable<Category> getAll();
    Optional<Category> findById(Integer categoryId);
    public List<CategoryDTO> getListCate();
    Optional<Category> findCategoryByProduct(Product product);

    String deleteCategory(Integer cateId);

    String updateCategory(CategoryDTO categoryDTO);

    CategoryDTO getById(Integer cateId);

    Category createCategory(CategoryDTO categoryDTO);
}
