package ingemark.assignment.task.shared.hnb;

import java.time.LocalDate;
import java.util.Optional;

public interface HnbUtil {

    Optional<HnbExchangeRateResponse> getExchangeRate(LocalDate date, String currency);
}
