package hcmute.it.furnitureshop.Service.Impl;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Repository.CategoryRepository;
import hcmute.it.furnitureshop.Service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest{


    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    CategoryRepository categoryRepository;
    Optional<Category> category = Optional.of(new Category());
    Optional<Category> category2 = Optional.of(new Category());
    Optional<Category> category3 = Optional.of(new Category());
    ArrayList<Category> listCate = new ArrayList<>();

    @Test
    public void findById() {
        //create data
        category.get().setCategoryId(1);
        category.get().setName("cate1");
        category2.get().setCategoryId(2);
        category2.get().setName("cate2");
        category3.get().setCategoryId(3);
        category3.get().setName("cate3");
        listCate.add(category.get());
        listCate.add(category2.get());
        listCate.add(category3.get());
        //
        when(categoryRepository.findById(1)).thenReturn(findCateById());
        //
        Optional<Category> category=categoryServiceImpl.findById(1);
        assertEquals("cate1",category.get().getName());
    }

    private Optional<Category> findCateById() {
        for(int i=0;i<listCate.size();i++){
            if(listCate.get(i).getCategoryId()==1)
                return Optional.of(listCate.get(i));
        }
        return null;
    }
}