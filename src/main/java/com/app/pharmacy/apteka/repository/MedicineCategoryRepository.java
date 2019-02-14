package com.app.pharmacy.apteka.repository;

import com.app.pharmacy.apteka.model.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory,Long> {
}
