package ingemark.assignment.task.shared.hnb;

import java.math.BigDecimal;

public class HnbTestHelper {

    public static HnbExchangeRateResponse mockHnbExchangeRateResponse(){
        return new HnbExchangeRateResponse(
                "145",
                "2024-07-26",
                "SAD",
                "USA",
                new BigDecimal(1.086700),
                new BigDecimal(1.083500),
                "840",
                new BigDecimal(1.085100),
                "USD"
        );
    }
}
