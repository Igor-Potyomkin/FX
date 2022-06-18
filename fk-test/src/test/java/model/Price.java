package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Price {

    private Long id;
    private String instrumentName;
    private BigDecimal bid;
    private BigDecimal ask;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", instrumentName='" + instrumentName + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", timestamp=" + timestamp +
                '}';
    }
}
