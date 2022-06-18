package subscriber.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import subscriber.constants.TimeFormatConstants;
import subscriber.model.Price;
import subscriber.parser.MessageParser;

import java.time.format.DateTimeFormatter;
import java.util.List;

@ActiveProfiles("test")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = MessageParser.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageParserTest {

    @Autowired
    private MessageParser parser;

    @Test
    void shouldParseMessageToPricesTest() {
        List<Price> prices = parser.parse(getCorrectMessage());

        Assertions.assertThat(prices).isNotNull();
        Assertions.assertThat(prices.size() == 1).isTrue();

        Price price = prices.get(0);
        Assertions.assertThat(price.getId().equals(106L)).isTrue();
        Assertions.assertThat(price.getInstrumentName().equals("EUR/USD")).isTrue();
        Assertions.assertThat(price.getBid().toString().equals("1.1000")).isTrue();
        Assertions.assertThat(price.getAsk().toString().equals("1.2000")).isTrue();

        String mappedTime = price.getTimestamp().format(DateTimeFormatter.ofPattern(TimeFormatConstants.TIMESTAMP_PATTERN));
        Assertions.assertThat(mappedTime.equals("01-06-2020 12:01:01.001")).isTrue();
    }

    @Test
    void shouldReturnEmptyPricesWhenMessageIsBrokenTest() {
        List<Price> prices = parser.parse(getBrokenMessage());
        Assertions.assertThat(prices.isEmpty()).isTrue();
    }

    private String getCorrectMessage() {
        return "106, EUR/USD, 1.1000, 1.2000, 01-06-2020 12:01:01.001";
    }

    private String getBrokenMessage() {
        return "107, EUR/JPY, 119.60, broken, 01-06-2020 12:01:02.002";
    }
}
