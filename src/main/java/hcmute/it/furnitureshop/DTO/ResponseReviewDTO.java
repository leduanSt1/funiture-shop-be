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
public class ResponseReviewDTO {
    private int responseReviewId;
    private int userId;
    private String name;
    private Date date;
    private String image;
    private String content;
}
