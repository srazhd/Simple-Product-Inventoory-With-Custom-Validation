package test.conroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test.model.Brand;
import test.service.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	private BrandService brandService;

	
	// Brand Homepage
	
	@RequestMapping(value = "/")
	public String brand(ModelMap map) {

		ArrayList<Brand> brandList = (ArrayList<Brand>) brandService.getAllBrand();
		map.addAttribute("brandList", brandList);

		return "brand";

	}
	
	// Brand Adding Form Page

	@RequestMapping(value = "/addBrand")
	public ModelAndView addBrand(ModelMap map) {
		return new ModelAndView("addBrand", "brand", new Brand());
	}

	// Brand Add Request
	
	@RequestMapping(value = "/addNewBrand", method = RequestMethod.POST)
	public String addBrand(ModelMap map, @ModelAttribute("brand") @Validated Brand brand, BindingResult result) {
		if (result.hasErrors()) {

			return "addBrand";
		}

		if (brandService.brandNameIsExist(brand.getName())) {
			result.rejectValue("name", "error.user", "Duplicate brand name will not be allow.");
			return "addBrand";
		}

		brandService.saveBrand(brand);

		ArrayList<Brand> brandList = (ArrayList<Brand>) brandService.getAllBrand();
		map.addAttribute("brandList", brandList);
		return "brand";
	}
	
	// Brand Editpage

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editBrand(ModelMap map, @PathVariable("id") Long id) {
		Brand brand = brandService.getBrand(id);

		return new ModelAndView("editBrand", "brand", brand);
	}

	// Brand Edit Request
	
	@RequestMapping(value = "/editBrand", method = RequestMethod.POST)
	public String updateBrand(ModelMap map, @ModelAttribute("brand") @Validated Brand brand, BindingResult result) {
		if (result.hasErrors()) {
			return "editBrand";
		}

		Brand brandOld = brandService.getBrand(brand.getId());

		if (!brand.getName().equals(brandOld.getName())) {
			if (brandService.brandNameIsExist(brand.getName())) {
				result.rejectValue("name", "error.user", "Duplicate brand name will not be allow.");
				return "editBrand";
			}
		}

		brandOld.setName(brand.getName());
		brandService.updateBrand(brandOld);

		ArrayList<Brand> brandList = (ArrayList<Brand>) brandService.getAllBrand();
		map.addAttribute("brandList", brandList);
		return "brand";
	}
	// Brand Delete Request
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteBrand(ModelMap map, @PathVariable("id") Long id) {
		brandService.deleteBrand(id);

		ArrayList<Brand> brandList = (ArrayList<Brand>) brandService.getAllBrand();
		map.addAttribute("brandList", brandList);
		return "brand";
	}
}
