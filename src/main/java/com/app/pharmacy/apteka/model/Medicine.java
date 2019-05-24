package com.app.pharmacy.apteka.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Date prod_date;

    private Date expire_date;

    private String license;

    private int cost;

    @ManyToOne
    @JoinColumn(name="quantity_type_id", nullable=true)
    private QuantityType quantity_type;

    @ManyToOne
    @JoinColumn(name="medicine_category_id", nullable=false)
    private MedicineCategory medicineCategory;

    @ManyToOne
    @JoinColumn(name="medicine_for_id", nullable=true)
    private MedicineFor medicineFor;


    @OneToMany(mappedBy="medicine")
    private Set<MedicineAmount> medicineAmounts;


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

    public MedicineCategory getMedicineCategory() {
        return medicineCategory;
    }

    public void setMedicineCategory(MedicineCategory medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    public MedicineFor getMedicineFor() {
        return medicineFor;
    }

    public void setMedicineFor(MedicineFor medicineFor) {
        this.medicineFor = medicineFor;
    }

    public Set<MedicineAmount> getMedicineAmounts() {
        return medicineAmounts;
    }

    public void setMedicineAmounts(Set<MedicineAmount> medicineAmounts) {
        this.medicineAmounts = medicineAmounts;
    }
}
