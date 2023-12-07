package hcmute.it.furnitureshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String name;
    private String phone;
    private String image;
    private String password;
    private String apartmentNumber;
    private String city;
    private String district;
    private String ward;
}
