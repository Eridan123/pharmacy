package com.app.pharmacy.apteka.repository;

import com.app.pharmacy.apteka.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
}
