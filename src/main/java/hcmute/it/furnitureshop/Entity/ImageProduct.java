package hcmute.it.furnitureshop.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "ImageProduct")
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageProductId")
    private int imageProductId;

    private String image;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
