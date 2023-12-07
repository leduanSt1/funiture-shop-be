package hcmute.it.furnitureshop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ResponseReview")
public class ResponseReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ResponseReviewId")
    private int responseReviewId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private Date date;

    private String content;
}
