package ingemark.assignment.task.shared.hnb;

import ingemark.assignment.task.shared.common.infrastructure.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HnbUtilImpl implements HnbUtil{
    private static final Logger logger = LoggerFactory.getLogger(HnbUtilImpl.class);
    private final String EXCHANGE_RATE_ENDPOINT = "tecajn-eur";
    private final String URL_SEPARATOR = "/";
    private final String API_VERSION = "v3";
    private final RestTemplate restTemplate;
    private final HnbConfig hnbConfig;


    public HnbUtilImpl(RestTemplate restTemplate, HnbConfig hnbConfig) {
        this.restTemplate = restTemplate;
        this.hnbConfig = hnbConfig;
    }

    @Override
    public Optional<HnbExchangeRateResponse> getExchangeRate(LocalDate date, String currency) {
        String stringDate = formatDate(date, "yyyy-MM-dd");
        String uppercaseCurrency = formatCurrency(currency);

        Map<String, String> parameterMap = Map.of(
                "datum-primjene", stringDate,
                "valuta", uppercaseCurrency
        );

        String parameters = buildParameters(parameterMap);
        String fullEndpoint = buildEndpoint(EXCHANGE_RATE_ENDPOINT, parameters);

        Optional<List> result = fetchData(fullEndpoint, List.class);

        return result.flatMap(this::mapToExchangeRateResponse);
    }

    private String formatDate(LocalDate date, String pattern) {
        return date == null ? null : DateUtil.formatDate(date, pattern);
    }

    private String formatCurrency(String currency) {
        return currency == null ? null : currency.toUpperCase();
    }
    private String buildParameters(Map<String, String> parameters) {
        return parameters.entrySet().stream()
                .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&", "?", ""));
    }

    private String buildEndpoint(String endpoint, String parameters) {
        return hnbConfig.HNB_DOMAIN + URL_SEPARATOR + endpoint + URL_SEPARATOR + API_VERSION + parameters;
    }

    private Optional<HnbExchangeRateResponse> mapToExchangeRateResponse(List<Map<String, Object>> result) {
        if (result.isEmpty()) {
            return Optional.empty();
        }

        Map<String, Object> data = result.get(0);

        return Optional.of(new HnbExchangeRateResponse(
                (String) data.get("broj_tecajnice"),
                (String) data.get("datum_primjene"),
                (String) data.get("drzava"),
                (String) data.get("drzava_iso"),
                parseBigDecimal(data.get("kupovni_tecaj").toString()),
                parseBigDecimal(data.get("prodajni_tecaj").toString()),
                (String) data.get("sifra_valute"),
                parseBigDecimal(data.get("srednji_tecaj").toString()),
                (String) data.get("valuta")
        ));
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.isEmpty()) {
            return BigDecimal.ZERO;
        }
        value = value.replace(',', '.');
        return new BigDecimal(value);
    }
    private <T> Optional<T> fetchData(String url, Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    responseType
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            logger.warn("Error during fetching data from HNB with URL: {}!", url);
            return Optional.empty();
        }
    }
}
