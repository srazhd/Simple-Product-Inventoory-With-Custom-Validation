package test.service;

import java.util.List;

import test.model.Brand;

public interface BrandService {
	public boolean saveBrand(Brand brand);
	public boolean updateBrand(Brand brand);
	public boolean deleteBrand(Long id);
	public Brand getBrand(Long id);
	public List<Brand> getAllBrand();
	public boolean brandNameIsExist(String name);
}
