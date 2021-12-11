package test.conroller;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dto.BrandDto;
import dto.ModelsDto;
import test.model.Models;
import test.service.BrandService;
import test.service.ItemsService;
import test.service.ModelsService;

@RestController
@ResponseBody
@RequestMapping("/api")
public class RestApi {
	@Autowired
	private ModelsService modelsService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ItemsService itemsService;

	@GetMapping(value = "/getmodels/{id}")
	public Object getModels(ModelMap map, @PathVariable("id") Long id) {

		List<Models> modelList = modelsService.getAllModelOfBrand(id);
		List<ModelsDto> modeldtoList = new ArrayList<>();
		modelList.forEach(model -> {
			ModelsDto modelDto = new ModelsDto();
			modelDto.setId(model.getId());
			modelDto.setName(model.getName());
			modeldtoList.add(modelDto);
		});
		return modeldtoList;
	}
}
