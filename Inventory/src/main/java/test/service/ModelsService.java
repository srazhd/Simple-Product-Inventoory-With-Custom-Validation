package test.service;

import java.util.List;

import test.model.Brand;
import test.model.Models;

public interface ModelsService {
	public boolean saveModel(Models models);
	public boolean updateModel(Models models);
	public boolean deleteModel(Long id);
	public Models getModel(Long id);
	public List<Models> getAllModel();
	public List<Models> getAllModelOfBrand(Long id);
	public boolean modelNameWithSameBrand(String name, Long brandId);
}
