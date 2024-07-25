package ingemark.assignment.task.shared.hnb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HnbExchangeRateResponse {
    private String brojTecajnice;
    private String datumPrimjene;
    private String drzava;
    private String drzavaIso;
    private BigDecimal kupovniTecaj;
    private BigDecimal prodajniTecaj;
    private String sifraValute;
    private BigDecimal srednjiTecaj;
    private String valuta;
}


