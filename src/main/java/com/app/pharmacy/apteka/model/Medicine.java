package com.app.pharmacy.apteka.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Date prod_date;

    private Date expire_date;

    private String license;

    private int amount;

    private int cost;

    @ManyToOne
    @JoinColumn(name="quantity_type_id", nullable=true)
    private QuantityType quantity_type;

    @ManyToOne
    @JoinColumn(name="medicine_category_id", nullable=false)
    private MedicineCategory medicine_category;

    @ManyToOne
    @JoinColumn(name="medicine_for_id", nullable=true)
    private MedicineFor medicine_for;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProd_date() {
        return prod_date;
    }

    public void setProd_date(Date prod_date) {
        this.prod_date = prod_date;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public QuantityType getQuantity_type() {
        return quantity_type;
    }

    public void setQuantity_type(QuantityType quantity_type) {
        this.quantity_type = quantity_type;
    }

    public MedicineCategory getMedicine_category() {
        return medicine_category;
    }

    public void setMedicine_category(MedicineCategory medicine_category) {
        this.medicine_category = medicine_category;
    }

    public MedicineFor getMedicine_for() {
        return medicine_for;
    }

    public void setMedicine_for(MedicineFor medicine_for) {
        this.medicine_for = medicine_for;
    }
}
