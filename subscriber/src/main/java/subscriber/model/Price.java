package subscriber.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import subscriber.constants.TimeFormatConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Price {

    private Long id;
    private String instrumentName;
    private BigDecimal bid;
    private BigDecimal ask;

    @JsonFormat(pattern = TimeFormatConstants.TIMESTAMP_PATTERN)
    private LocalDateTime timestamp;

    public void processMargin() {
        this.bid = bid.subtract(new BigDecimal("0.001"));
        this.ask = bid.add(new BigDecimal("0.001"));
    }
}
