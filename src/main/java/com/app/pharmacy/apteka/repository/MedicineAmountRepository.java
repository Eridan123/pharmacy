package com.app.pharmacy.apteka.repository;

import com.app.pharmacy.apteka.model.MedicineAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineAmountRepository extends JpaRepository<MedicineAmount,Long> {
}
