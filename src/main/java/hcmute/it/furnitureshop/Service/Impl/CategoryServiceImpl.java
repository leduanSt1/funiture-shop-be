package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.DTO.CategoryDTO;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Room;
import hcmute.it.furnitureshop.Repository.CategoryRepository;
import hcmute.it.furnitureshop.Repository.RoomRepository;
import hcmute.it.furnitureshop.Service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository ;
    @Autowired
    RoomRepository roomRepository;


    @Override
    public Iterable<Category> getCategoriesByRoom(Integer roomId) {
        Optional<Room> room=roomRepository.findById(roomId);
        return categoryRepository.findCategoriesByRoom(room);
    }

    @Override
    public Iterable<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer categoryId){
        return categoryRepository.findById(categoryId);
    }
    @Override
    public List<CategoryDTO> getListCate() {
        List<CategoryDTO> categoryDTOS= new ArrayList<>();
        categoryRepository.findAll().forEach(category -> {
            categoryDTOS.add(CategoryDTO.builder().categoryId(category.getCategoryId())
                    .name(category.getName())
                    .image(category.getImage())
                    .build());

        });
        return categoryDTOS;
    }

    @Override
    public Optional<Category> findCategoryByProduct(Product product) {
        return categoryRepository.findCategoryByProduct(product);
    }

    @Override
    public String deleteCategory(Integer cateId) {
        Optional<Category> category = categoryRepository.findById(cateId);
        if(category.isPresent())
        {
            categoryRepository.deleteById(cateId);
            return "Xóa loại sản phẩm thành công";
        }
        return "Xóa loại sản phẩm thất bại";
    }

    @Override
    public String updateCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO getById(Integer cateId) {
        Optional<Category> category = categoryRepository.findById(cateId);
        if(category.isPresent())
            return CategoryDTO.builder().categoryId(category.get().getCategoryId())
                    .image(category.get().getImage())
                    .name(category.get().getName())
                    .numberProduct(category.get().getProduct().size())
                    .build();
        return null;
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        if(!categoryDTO.getName().isEmpty()) {
            Optional<Category> cate = Optional.ofNullable(categoryRepository.findByName(categoryDTO.getName()));
            if (cate.isEmpty()) {
                Category category = Category.builder().image(categoryDTO.getImage())
                        .name(categoryDTO.getName())
                        .build();
                categoryRepository.save(category);
                return category;
            }
        }
        return null;
    }
}
