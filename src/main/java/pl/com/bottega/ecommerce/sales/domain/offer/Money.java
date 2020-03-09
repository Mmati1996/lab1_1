package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private BigDecimal denomination;

    private String currency;

    public BigDecimal getDenomination() {
        return denomination;
    }

    public Money(BigDecimal denomination, String currency) {
        this.denomination = denomination;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Money other = (Money) obj;
        return Objects.equals(currency, other.currency)
               && Objects.equals(denomination, other.denomination);
    }

}