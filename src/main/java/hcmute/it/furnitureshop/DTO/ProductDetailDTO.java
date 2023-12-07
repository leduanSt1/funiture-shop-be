package hcmute.it.furnitureshop.DTO;

import hcmute.it.furnitureshop.Entity.ImageProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private int productId;
    private String name;
    private long price;
    private long quantity;
    private List<ImageProduct> imageProducts;
    private String description;
    private String material;
    private int numberProductSold;
    private String size;
    private String status;
    private String categoryName;
    private Double percentDiscount;
    private String title;
    private int numberFavorite;
    private int numberRating;

}
