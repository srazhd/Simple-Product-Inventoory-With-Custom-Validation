package test.serviceImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.model.Items;
import test.model.Models;
import test.service.ItemsService;

@Service("itemsService")
@Transactional
public class ItemsServiceImpl implements ItemsService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveItem(Items items) {
		Date date = new Date(System.currentTimeMillis());
		items.setEntryDate(date);
		Session session = this.sessionFactory.openSession();

		session.saveOrUpdate(items);
		return true;
	}

	@Override
	public boolean updateItem(Items items) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(items);
		return true;
	}

	@Override
	public boolean deleteItem(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Items item = session.get(Items.class, id);
		session.delete(item);
		return true;
	}

	@Override
	public Items getItem(Long id) {
		Session session = this.sessionFactory.openSession();
		Items items = session.get(Items.class, id);
		return items;
	}

	@Override
	public List<Items> getAllItems() {
		Session session = this.sessionFactory.openSession();
		List<Items> itemsList = session.createQuery("from Items").list();
		return itemsList;
	}

	@Override
	public boolean itemNameWithSameBrandSameModel(String name, Long brandId, Long modelId) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(
				"select count(*) from Items items where items.name=:name and items.brand.id=:bid and items.models.id=:mid");
		query.setString("name", name);
		query.setLong("bid", brandId);
		query.setLong("mid", modelId);
		Long count = (Long) query.uniqueResult();
		return count > 0;
	}

}
