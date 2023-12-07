package hcmute.it.furnitureshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCheckOutDTO {
    ArrayList<Integer> productIds;
    ArrayList<Integer> counts;
    Boolean nowDelivery;
}
