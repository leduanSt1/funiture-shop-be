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
public class OrderDashboardDTO implements Comparable<OrderDashboardDTO>{
    private Integer orderId;
    private Date dateUpdate;
    private long total;
    private String userName;

    @Override
    public int compareTo(OrderDashboardDTO o) {
        return o.getDateUpdate().compareTo(this.dateUpdate);
    }
}
