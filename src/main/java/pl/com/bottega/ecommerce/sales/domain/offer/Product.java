package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Product {
    private String Id;

    private String Type;

    private String Name;

    private Date SnapshotDate;

    public String getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public String getName() {
        return Name;
    }

    public Date getSnapshotDate() {
        return SnapshotDate;
    }

    public Product(String id, String type, String name, Date snapshotDate) {
        Id = id;
        Type = type;
        Name = name;
        SnapshotDate = snapshotDate;
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
        Product other = (Product) obj;
        return Objects.equals(Id,((Product) obj).Id)
        && Objects.equals(Name, other.Name)
        && Objects.equals(SnapshotDate, other.SnapshotDate)
        && Objects.equals(Type, other.Type);

    }

    public boolean sameAs(Product other) {
        if (Name == null) {
            if (other.Name != null) {
                return false;
            }
        } else if (!Name.equals(other.Name)) {
            return false;
        }

        if (Id == null) {
            if (other.Id != null) {
                return false;
            }
        } else if (!Id.equals(other.Id)) {
            return false;
        }

        if (Type == null) {
            if (other.Type != null) {
                return false;
            }
        } else if (!Type.equals(other.Type)) {
            return false;
        }

        return true;
    }

}