package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.Medicine;
import com.app.pharmacy.apteka.model.MedicineCategory;
import com.app.pharmacy.apteka.model.MedicineFor;
import com.app.pharmacy.apteka.repository.MedicineCategoryRepository;
import com.app.pharmacy.apteka.repository.MedicineForRepository;
import com.app.pharmacy.apteka.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    MedicineForRepository medicineForRepository;

    @RequestMapping(value = {"","/list"})
    public String listMedicine(ModelMap model){

        List<Medicine> medicines=medicineRepository.findAll();
//        List<MedicineCategory> medicineCategories=medicineCategoryRepository.findAll();
//        List<MedicineFor> medicineFors=medicineForRepository.findAll();

        model.addAttribute("medicines",medicines);

        return "medicine/list";
    }

    @RequestMapping(value = {"/{id}/view"})
    public String listMedicine(ModelMap model,@PathVariable("id") Long id){

        Medicine medicine=medicineRepository.getOne(id);

        model.addAttribute("medicine",medicine);
        model.addAttribute("categories",medicineCategoryRepository.findAll());
        model.addAttribute("fors",medicineForRepository.findAll());

        return "medicine/view";
    }

    @RequestMapping("/{id}/save")
    public String saveView(ModelMap model, @PathVariable("id") Long id){

        if(id==0){
            model.addAttribute("medicine",new Medicine());
        }

        else {
            Medicine medicine=medicineRepository.getOne(id);
            model.addAttribute("medicine",medicine);
        }
        model.addAttribute("categories",medicineCategoryRepository.findAll());
        model.addAttribute("fors",medicineForRepository.findAll());

        return "medicine/form";

    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String savePostView(ModelMap model,Medicine medicine){

        if(medicine.getId()==null){
            medicineRepository.save(medicine);
            return "redirect:/medicine/"+medicine.getId()+"/view";
        }
        else {
            medicineRepository.save(medicine);
            return "redirect:/medicine/list";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        Medicine medicine=medicineRepository.getOne(id);
        medicineRepository.delete(medicine);

        return "redirect:/medicine/list";
    }

}
