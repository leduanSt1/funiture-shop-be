package hcmute.it.furnitureshop.DTO;


import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDTO {
    private String nickname;
    private String content;
    private Date timestamp;
    private String userId;
    private String userImage;
}
