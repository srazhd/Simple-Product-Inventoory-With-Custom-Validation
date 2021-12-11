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
public class HomeController {
	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/")
	public String brand(ModelMap map) {

		ArrayList<Brand> brandList = (ArrayList<Brand>) brandService.getAllBrand();
		map.addAttribute("brandList", brandList);

		return "brand";

	}

}
