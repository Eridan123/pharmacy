package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.Medicine;
import com.app.pharmacy.apteka.model.MedicineCategory;
import com.app.pharmacy.apteka.repository.MedicineCategoryRepository;
import com.app.pharmacy.apteka.repository.MedicineForRepository;
import com.app.pharmacy.apteka.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/category")
public class MedicineCategoryController {

    @Autowired
    MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    MedicineForRepository medicineForRepository;

    @RequestMapping(value = {"","/list"})
    public String listMedicine(ModelMap model){

        List<MedicineCategory> medicineCategories=medicineCategoryRepository.findAll();

        model.addAttribute("categories",medicineCategories);

        return "category/list";
    }

//    @RequestMapping(value = {"/{id}/view"})
//    public String listMedicine(ModelMap model,@PathVariable("id") Long id){
//
//        MedicineCategory medicineCategory=medicineCategoryRepository.getOne(id);
//
//        model.addAttribute("category",medicineCategory);
//
//        return "category/view";
//    }

    @RequestMapping("/{id}/save")
    public String saveView(ModelMap model, @PathVariable("id") Long id){

        if(id==0){
            model.addAttribute("category",new MedicineCategory());
        }

        else {
            MedicineCategory medicineCategory=medicineCategoryRepository.getOne(id);
            model.addAttribute("category",medicineCategory);
        }

        return "category/form";

    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String savePostView(MedicineCategory category){

        if(category.getId()==null){
            medicineCategoryRepository.save(category);
        }
        else {
            MedicineCategory category1=medicineCategoryRepository.getOne(category.getId());
            category1.setName(category.getName());
            category1.setDescription(category.getDescription());
            medicineCategoryRepository.save(category1);
        }

        return "redirect:/category/list";

    }

    @RequestMapping(value = {"/{id}/medicines"}, method = RequestMethod.GET)
    public String medicines(Model model, @PathVariable("id") Long id){

        MedicineCategory category=medicineCategoryRepository.getOne(id);
        List<Medicine> list=medicineRepository.findMedicinesByMedicineCategory(category);

        model.addAttribute("list",list);
        model.addAttribute("categories",medicineCategoryRepository.findAll());
        model.addAttribute("fors",medicineForRepository.findAll());

        return "home";

    }
}
