package hcmute.it.furnitureshop.DTO;

import hcmute.it.furnitureshop.Entity.Discount;
import hcmute.it.furnitureshop.Entity.ImageProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int productId;
    private String name;
    private long price;
    private long quantity;
    private List<ImageProduct> imageProducts;
    private String description;
    private String material;
    private String size;
    private String status;
    private int numberProductSold;
    private Discount discount;
    private Date dateImport;
}
