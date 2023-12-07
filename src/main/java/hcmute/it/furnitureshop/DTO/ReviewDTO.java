package hcmute.it.furnitureshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private int reviewId;
    private int userId;
    private String name;
    private String content;
    private String image;
    private Date date;

}
