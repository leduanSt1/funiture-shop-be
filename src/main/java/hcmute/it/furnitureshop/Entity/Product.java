package hcmute.it.furnitureshop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;

    @Column(name = "name", columnDefinition = "nvarchar(100) not null")
    private String name;


    @Column(name = "price", columnDefinition = "int not null")
    private long price;

    @Min(value =0)
    @Column(name = "quantity", columnDefinition = "int not null")
    private long quantity;


    @JsonManagedReference
    @OneToMany(mappedBy="product",cascade = CascadeType.ALL)
    private List<ImageProduct> imageProducts;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name="material",columnDefinition = "text")
    private String material;

    @Column(name="size",columnDefinition = "text")
    private String size;

    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy="product",cascade = CascadeType.ALL)
    private List<Review> reviews;

    @JsonManagedReference
    @OneToMany(mappedBy="product",cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @JsonManagedReference
    @OneToMany(mappedBy="product",cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @JsonManagedReference
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Order> order;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;

    private int numberProductSold;
    private Date dateImport;

    @OneToOne(mappedBy = "product")
    private Banner banner;

}