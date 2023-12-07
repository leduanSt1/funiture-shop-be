package hcmute.it.furnitureshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BestUser implements Comparable<BestUser>{
    private Integer userId;
    private String name;
    private Integer point;
    private String rank;
    @Override
    public int compareTo(BestUser o) {
        return o.getPoint().compareTo(this.point);
    }
}
