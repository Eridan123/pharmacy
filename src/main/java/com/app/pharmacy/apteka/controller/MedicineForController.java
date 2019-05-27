package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.Medicine;
import com.app.pharmacy.apteka.model.MedicineFor;
import com.app.pharmacy.apteka.repository.MedicineCategoryRepository;
import com.app.pharmacy.apteka.repository.MedicineForRepository;
import com.app.pharmacy.apteka.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/medicineFor")
public class MedicineForController {

    @Autowired
    MedicineForRepository medicineForRepository;

    @Autowired
    MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    MedicineRepository medicineRepository;


    @RequestMapping("/list")
    public String list(ModelMap model){
        model.addAttribute("list",medicineForRepository.findAll());

        return "medicinefor/list";
    }

    @RequestMapping("/{id}/save")
    public String saveGet(ModelMap model, @PathVariable("id") Long id){
        if(id==null || id==0){
            model.addAttribute("model",new MedicineFor());
        }
        else{
            MedicineFor medicineFor=medicineForRepository.getOne(id);
            model.addAttribute("model",medicineFor);
        }
        return "/medicinefor/form";
    }
    @PostMapping("/save")
    public String savePost(MedicineFor medicineFor){
        if(medicineFor.getId()==null||medicineFor.getId()==0){
            medicineForRepository.save(medicineFor);
        }
        else{
            medicineForRepository.save(medicineFor);
        }

        return "redirect:/medicineFor/list";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        MedicineFor medicineFor=medicineForRepository.getOne(id);
        medicineForRepository.delete(medicineFor);

        return "redirect:/medicineFor/list";
    }

    @RequestMapping("/{id}/medicines")
    public String medicines(Model model, @PathVariable("id") Long id){

        MedicineFor medicineFor=medicineForRepository.getOne(id);
        List<Medicine> list=medicineRepository.findMedicinesByMedicineFor(medicineFor);

        model.addAttribute("list",list);
        model.addAttribute("categories",medicineCategoryRepository.findAll());
        model.addAttribute("fors",medicineForRepository.findAll());

        return "home";

    }
}
