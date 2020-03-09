/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    private BigDecimal productPrice;


    private int quantity;

    public Product getProduct() {
        return product;
    }

    private Money totalCost;

    private Product product;

    private String discountCause;

    private BigDecimal discount;

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity, BigDecimal discount, String discountCause) {

        this.product = new Product(productId,productType,productName,productSnapshotDate);
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount);
        }

        this.totalCost = (new Money ((new BigDecimal(quantity)),null));
    }



    public BigDecimal getProductPrice() {
        return productPrice;
    }





    public Money getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return getTotalCost().getCurrency();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCost, discount, discountCause, productPrice, quantity, product);
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
        OfferItem other = (OfferItem) obj;
        return Objects.equals(discount, other.discount)
               && Objects.equals(discountCause, other.discountCause)
               && Objects.equals(productPrice, other.productPrice)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost)
               && Objects.equals(product,other.product);
    }

    /**
     *
     /* @param item
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (productPrice == null) {
            if (other.productPrice != null) {
                return false;
            }
        } else if (!productPrice.equals(other.productPrice)) {
            return false;
        }

        if (product.sameAs(other.product)){
            return false;
        }


        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.getDenomination().compareTo(other.getTotalCost().getDenomination()) > 0) {
            max = totalCost.getDenomination();
            min = other.getTotalCost().getDenomination();
        } else {
            max = other.getTotalCost().getDenomination();
            min = totalCost.getDenomination();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
/*
totalCost jako operacja

osobna klasa produkt
produkt 1--<+>n OfferItem

OfferItem:


produkt:
    id
    type
    name
    snapshotDate


 money:
    denomination
    currency

    money --discount--<+>OfferItem
    money --totalCost--<+> OfferItem
    produkt --price--<+> money


 */
