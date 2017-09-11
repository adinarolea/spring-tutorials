package com.spring.data.tutorial.querydsl;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purse")
public class Purse {
    @Id
    private String id;

    private BigDecimal width;

    @Column(name = "plength")
    private BigDecimal length;

    @Column(name = "no_pockets")
    private int noPockets;

    @Column(name = "purse_type")
    @Enumerated(EnumType.STRING)
    private PurseType purseType;

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public int getNoPockets() {
        return noPockets;
    }

    public void setNoPockets(int noPockets) {
        this.noPockets = noPockets;
    }

    public PurseType getPurseType() {
        return purseType;
    }

    public void setPurseType(PurseType purseType) {
        this.purseType = purseType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public enum PurseType {
        SPORT, CLASSY, CASUAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purse purse = (Purse) o;

        if (noPockets != purse.noPockets) return false;
        if (id != null ? !id.equals(purse.id) : purse.id != null) return false;
        if (width != null ? width.compareTo(purse.width)!=0 : purse.width != null) return false;
        if (length != null ? length.compareTo(purse.length)!=0 : purse.length != null) return false;
        return purseType.equals(purse.purseType);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + noPockets;
        result = 31 * result + (purseType != null ? purseType.hashCode() : 0);
        return result;
    }
}
