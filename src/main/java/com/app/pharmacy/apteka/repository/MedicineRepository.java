package com.app.pharmacy.apteka.repository;

import com.app.pharmacy.apteka.model.Medicine;
import com.app.pharmacy.apteka.model.MedicineCategory;
import com.app.pharmacy.apteka.model.MedicineFor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    public List<Medicine> findMedicinesByMedicineCategory(MedicineCategory medicineCategory);
    public List<Medicine> findMedicinesByMedicineFor(MedicineFor medicineFor);
}
