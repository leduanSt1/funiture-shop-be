package hcmute.it.furnitureshop.DTO;

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
public class OrderDTO {
    private Integer orderId;
    private int count;
    private String state;
    private Date date;
    private Date dateUpdate;
    private Boolean paid;
    private Boolean nowDelivery;
    private Integer productId;
    private String productName;
    private List<ImageProduct> imageProducts;
    private long productPrice;
    private String userName;
    private long total;
}
