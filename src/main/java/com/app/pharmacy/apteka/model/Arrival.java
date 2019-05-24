package com.app.pharmacy.apteka.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "arrival")
public class Arrival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="arrival")
    private Set<MedicineAmount> medicineAmounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MedicineAmount> getMedicineAmounts() {
        return medicineAmounts;
    }

    public void setMedicineAmounts(Set<MedicineAmount> medicineAmounts) {
        this.medicineAmounts = medicineAmounts;
    }
}
