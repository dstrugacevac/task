package ingemark.assignment.task.shared.common.infrastructure.date;


import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtil {

    private static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.UTC;

    private DateUtil() {
    }

    public static OffsetDateTime now() {
        return new Date().toInstant().atOffset(DEFAULT_ZONE_OFFSET);
    }

    public static String formatDate(LocalDate date, String pattern) {
        if (pattern == null || date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
}
