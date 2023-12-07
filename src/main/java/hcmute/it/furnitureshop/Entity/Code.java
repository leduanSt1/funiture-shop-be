package hcmute.it.furnitureshop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CodeSale")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codeId")
    private Integer codeId;

    @JsonBackReference
    @OneToMany(mappedBy="code",cascade = CascadeType.ALL)
    private List<Order> order;

    private String description;
    private int priceCode;
    private Boolean state;
}
