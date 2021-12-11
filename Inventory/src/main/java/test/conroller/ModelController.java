package test.conroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import test.model.Models;
import test.service.BrandService;
import test.service.ModelsService;

@Controller
@RequestMapping("/models")
public class ModelController {
	@Autowired
	private ModelsService modelsService;
	@Autowired
	private BrandService brandService;

	// Model Homepage
	
	@RequestMapping(value = "/")
	public String model(ModelMap map) {

		ArrayList<Models> modelList = (ArrayList<Models>) modelsService.getAllModel();
		map.addAttribute("modelList", modelList);

		return "models";

	}

	// Model Add new Items
	
	@RequestMapping(value = "/addModels")
	public ModelAndView addModels(ModelMap map) {

		map.addAttribute("brandMap", getBrands());

		Models models = new Models();
		return new ModelAndView("addModels", "model", models);
	}

	public Map<Long, String> getBrands() {

		Map<Long, String> brandMap = new HashMap<>();

		List<Brand> brandList = brandService.getAllBrand();
		brandList.forEach(brand -> {
			brandMap.put(brand.getId(), brand.getName());
		});
		return brandMap;
	}
	
	
	// Model Add request
	
	@RequestMapping(value = "/addNewModels", method = RequestMethod.POST)
	public String addModels(ModelMap map, @ModelAttribute("model") @Validated Models model, BindingResult result) {

		if (model.getBrand().getId() < 1) {
			result.rejectValue("brand.id", "error.user", "Brand Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			return "addModels";
		}

		if (result.hasErrors()) {
			map.addAttribute("brandMap", getBrands());
			return "addModels";
		}

		if (modelsService.modelNameWithSameBrand(model.getName(), model.getBrand().getId())) {
			result.rejectValue("name", "error.user", "Duplicate model name of same brand will not be allow.");
			map.addAttribute("brandMap", getBrands());
			return "addModels";
		}

		modelsService.saveModel(model);

		ArrayList<Models> modelList = (ArrayList<Models>) modelsService.getAllModel();
		map.addAttribute("modelList", modelList);
		return "models";
	}
	
	
	// Model edit page
	
	
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editModels(ModelMap map, @PathVariable("id") Long id) {
		Models models = modelsService.getModel(id);
		map.addAttribute("brandMap", getBrands());

		return new ModelAndView("editModels", "model", models);
	}

	@RequestMapping(value = "/editModels", method = RequestMethod.POST)
	public String updateModels(ModelMap map, @ModelAttribute("model") @Validated Models model, BindingResult result) {
		if (model.getBrand().getId() < 1) {
			result.rejectValue("brand.id", "error.user", "Brand Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			return "editModels";
		}

		if (result.hasErrors()) {
			map.addAttribute("brandMap", getBrands());
			return "editModels";
		}

		Models modelOld = modelsService.getModel(model.getId());

		if (model.getBrand().getId() == modelOld.getBrand().getId() && !model.getName().equals(modelOld.getName())) {
			if (modelsService.modelNameWithSameBrand(model.getName(), model.getBrand().getId())) {
				result.rejectValue("name", "error.user", "Duplicate model name of same brand will not be allow.");
				map.addAttribute("brandMap", getBrands());
				return "addModels";
			}

		}

		if (model.getBrand().getId() != modelOld.getBrand().getId()) {
			if (modelsService.modelNameWithSameBrand(model.getName(), model.getBrand().getId())) {
				result.rejectValue("name", "error.user", "Duplicate model name of same brand will not be allow.");
				map.addAttribute("brandMap", getBrands());
				return "addModels";
			}

		}

		model.setEntryDate(modelOld.getEntryDate());

		modelsService.updateModel(model);

		ArrayList<Models> modelList = (ArrayList<Models>) modelsService.getAllModel();
		map.addAttribute("modelList", modelList);
		return "models";
	}

	// Model delete request
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteModels(ModelMap map, @PathVariable("id") Long id) {
		modelsService.deleteModel(id);

		ArrayList<Models> modelList = (ArrayList<Models>) modelsService.getAllModel();
		map.addAttribute("modelList", modelList);
		return "models";
	}
}
