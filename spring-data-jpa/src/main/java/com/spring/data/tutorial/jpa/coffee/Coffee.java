package com.spring.data.tutorial.jpa.coffee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "coffee")
public class Coffee implements Serializable{
    @Id
    private String id;

    private BigDecimal price;

    private String origin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(id, coffee.id) &&
                Objects.equals(price, coffee.price) &&
                Objects.equals(origin, coffee.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, origin);
    }
}
