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
import test.model.Items;
import test.model.Models;
import test.model.Items;
import test.service.BrandService;
import test.service.ItemsService;
import test.service.ModelsService;
import test.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ModelsService modelsService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ItemsService itemsService;

	@RequestMapping(value = "/")
	public String model(ModelMap map) {

		ArrayList<Items> itemList = (ArrayList<Items>) itemsService.getAllItems();
		map.addAttribute("itemList", itemList);

		return "items";

	}
	
	// Add Item Homepage

	@RequestMapping(value = "/addItems")
	public ModelAndView addItems(ModelMap map) {

		map.addAttribute("brandMap", getBrands());
//		map.addAttribute("modelMap", getModels());

		Items models = new Items();
		return new ModelAndView("addItems", "item", models);
	}

	public Map<Long, String> getBrands() {

		Map<Long, String> brandMap = new HashMap<>();

		List<Brand> brandList = brandService.getAllBrand();
		brandList.forEach(brand -> {
			brandMap.put(brand.getId(), brand.getName());
		});
		return brandMap;
	}

	public Map<Long, String> getModels() {

		Map<Long, String> modelMap = new HashMap<>();

		List<Models> modelList = modelsService.getAllModel();
		modelList.forEach(model -> {
			modelMap.put(model.getId(), model.getName());
		});
		return modelMap;
	}

	// Add Item Request
	
	@RequestMapping(value = "/addNewItems", method = RequestMethod.POST)
	public String addItems(ModelMap map, @ModelAttribute("item") @Validated Items item, BindingResult result) {
		if (item.getBrand().getId() < 1 && item.getModels().getId() < 1) {
			result.rejectValue("brand.id", "error.user", "Brand Name Is Mandatory.");
			result.rejectValue("models.id", "error.user", "Model Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}
		if (item.getBrand().getId() < 1) {
			result.rejectValue("brand.id", "error.user", "Brand Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}

		if (item.getModels().getId() < 1) {
			result.rejectValue("models.id", "error.user", "Model Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}

		if (result.hasErrors()) {
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}

		if (itemsService.itemNameWithSameBrandSameModel(item.getName(), item.getBrand().getId(),
				item.getModels().getId())) {
			result.rejectValue("name", "error.user",
					"Duplicate item name of same brand and same model will not be allow.");
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}

		itemsService.saveItem(item);

		ArrayList<Items> itemList = (ArrayList<Items>) itemsService.getAllItems();
		map.addAttribute("itemList", itemList);
		return "items";
	}
	
	// Item Edit page
	
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editItems(ModelMap map, @PathVariable("id") Long id) {
		Items items = itemsService.getItem(id);
		map.addAttribute("brandMap", getBrands());
		map.addAttribute("modelMap", getModels());

		return new ModelAndView("editItems", "item", items);
	}

	// Item  Edit Request
	
	@RequestMapping(value = "/editItems", method = RequestMethod.POST)
	public String updateItems(ModelMap map, @ModelAttribute("item") @Validated Items item, BindingResult result) {

		if (item.getBrand().getId() < 1 && item.getModels().getId() < 1) {
			result.rejectValue("brand.id", "error.user", "Brand Name Is Mandatory.");
			result.rejectValue("models.id", "error.user", "Model Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}

		if (item.getBrand().getId() < 1) {
			result.rejectValue("brand.id", "error.user", "Brand Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}

		if (item.getModels().getId() < 1) {
			result.rejectValue("models.id", "error.user", "Model Name Is Mandatory.");
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}

		if (result.hasErrors()) {
			map.addAttribute("brandMap", getBrands());
			map.addAttribute("modelMap", getModels());
			return "addItems";
		}
		Items itemOld = itemsService.getItem(item.getId());

		if (item.getBrand().getId() == itemOld.getBrand().getId()
				&& item.getModels().getId() == itemOld.getModels().getId()
				&& !item.getName().equals(itemOld.getName())) {

			if (itemsService.itemNameWithSameBrandSameModel(item.getName(), item.getBrand().getId(),
					item.getModels().getId())) {
				map.addAttribute("brandMap", getBrands());
				map.addAttribute("modelMap", getModels());
				result.rejectValue("name", "error.user",
						"Duplicate item name of same brand and same model will not be allow.");
				return "addItems";
			}

		}

		if (item.getBrand().getId() != itemOld.getBrand().getId()
				|| item.getModels().getId() != itemOld.getModels().getId()) {

			if (itemsService.itemNameWithSameBrandSameModel(item.getName(), item.getBrand().getId(),
					item.getModels().getId())) {
				map.addAttribute("brandMap", getBrands());
				map.addAttribute("modelMap", getModels());
				result.rejectValue("name", "error.user",
						"Duplicate item name of same brand and same model will not be allow.");
				return "addItems";
			}

		}

		item.setEntryDate(itemOld.getEntryDate());

		itemsService.updateItem(item);

		ArrayList<Items> itemList = (ArrayList<Items>) itemsService.getAllItems();
		map.addAttribute("itemList", itemList);
		return "items";
	}
	
	// Item Delete Request
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteItems(ModelMap map, @PathVariable("id") Long id) {
		itemsService.deleteItem(id);

		ArrayList<Items> itemList = (ArrayList<Items>) itemsService.getAllItems();
		map.addAttribute("itemList", itemList);
		return "items";
	}

}
