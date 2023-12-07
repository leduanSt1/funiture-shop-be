package hcmute.it.furnitureshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataChartDTO implements Comparable<DataChartDTO>{
    private Integer productId;
    private Long revenue;
    private String productName;
    @Override
    public int compareTo(DataChartDTO other) {
        return other.getRevenue().compareTo(this.revenue);
    }
}
