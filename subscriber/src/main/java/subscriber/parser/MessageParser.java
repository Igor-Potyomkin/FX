package subscriber.parser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import subscriber.constants.TimeFormatConstants;
import subscriber.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class MessageParser {
    private static final Pattern CSV_PRICE_FEED_PATTERN = Pattern.compile(
            "((?:^)\\s*(?:\\d+)(?=,|))\\s*,\\s*(\\w{3}\\/\\w{3}\\s*),\\s*([+-]?\\d+\\.?\\d*|\\.\\d+)\\s*,\\s*([+-]?\\d+\\.?\\d*|\\.\\d+)\\s*,\\s*(\\d\\d-\\d\\d-\\d{4}\\s\\d\\d:\\d\\d:\\d\\d\\.\\d{3})($)");

    private static final int ID_GROUP_NUMBER = 1;
    private static final int NAME_GROUP_NUMBER = 2;
    private static final int BID_GROUP_NUMBER = 3;
    private static final int ASK_GROUP_NUMBER = 4;
    private static final int TIME_GROUP_NUMBER = 5;

    public List<Price> parse(String message) {
        return Arrays.stream(message.split("\n...\n"))
                .map(this::parseLine)
                .filter(Objects::nonNull)
                .toList();
    }

    private Price parseLine(String line) {
        Matcher matcher = CSV_PRICE_FEED_PATTERN.matcher(line);
        return matcher.matches()
                ? mapToPrice(matcher)
                : null;
    }

    private Price mapToPrice(Matcher matcher) {
        return Price.builder()
                .id(Long.valueOf(matcher.group(ID_GROUP_NUMBER)))
                .instrumentName(matcher.group(NAME_GROUP_NUMBER))
                .bid(new BigDecimal(matcher.group(BID_GROUP_NUMBER)))
                .ask(new BigDecimal(matcher.group(ASK_GROUP_NUMBER)))
                .timestamp(
                        LocalDateTime.parse(
                                matcher.group(TIME_GROUP_NUMBER),
                                DateTimeFormatter.ofPattern(TimeFormatConstants.TIMESTAMP_PATTERN))
                )
                .build();
    }
}
