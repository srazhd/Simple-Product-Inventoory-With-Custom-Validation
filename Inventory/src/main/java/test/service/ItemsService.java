package test.service;

import java.util.List;

import test.model.Brand;
import test.model.Items;

public interface ItemsService {
	public boolean saveItem(Items items);
	public boolean updateItem(Items items);
	public boolean deleteItem(Long id);
	public Items getItem(Long id);
	public List<Items> getAllItems();
	public boolean itemNameWithSameBrandSameModel(String name, Long brandId, Long modelId);
}
