package ingemark.assignment.task.shared.hnb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HnbConfig {

    @Value("${hnb.domain}")
    public String HNB_DOMAIN;
}
