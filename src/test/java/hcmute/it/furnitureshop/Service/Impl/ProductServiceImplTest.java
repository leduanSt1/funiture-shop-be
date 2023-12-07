package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Repository.CategoryRepository;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Service.ProductService;
import junit.framework.TestCase;
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
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    ProductRepository productRepository;
    Optional<Product> product = Optional.of(new Product());
    Optional<Product> product2 = Optional.of(new Product());
    Optional<Product> product3 = Optional.of(new Product());
    ArrayList<Product> products=new ArrayList<>();

    @Test
    public void getProductById() {
        //create data
        product.get().setProductId(1);
        product.get().setName("sofa product");
        product2.get().setProductId(1);
        product2.get().setName("sofa product");
        product3.get().setProductId(1);
        product3.get().setName("sofa product");
        products.add(product.get());
        products.add(product2.get());
        products.add(product3.get());
        //
        when(productRepository.findById(1)).thenReturn(findProductById());
        //
        Optional<Product> product=productServiceImpl.findById(1);
        assertEquals("sofa produc",product.get().getName());
    }
    private Optional<Product> findProductById() {
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProductId()==1)
                return Optional.of(products.get(i));
        }
        return null;
    }
}