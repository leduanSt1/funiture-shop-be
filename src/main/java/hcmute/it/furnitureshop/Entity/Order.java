package hcmute.it.furnitureshop.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "Orders")
public class Order implements Serializable{
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer orderId;
    private int count;
    private String state;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "codeId")
    private Code code;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @JsonBackReference
    @OneToMany(mappedBy="order",cascade = CascadeType.ALL)
    private List<Notification> notification;

    private Date date;

    private Date dateUpdate;

    private Boolean paid;

    private Boolean nowDelivery;
}