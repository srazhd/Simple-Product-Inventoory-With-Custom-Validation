package test.serviceImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.model.Brand;
import test.model.Models;
import test.service.ModelsService;

@Service("modelsService")
@Transactional
public class ModelsServiceImpl implements ModelsService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveModel(Models models) {
		Date date = new Date(System.currentTimeMillis());
		models.setEntryDate(date);
		Session session = this.sessionFactory.openSession();

		session.saveOrUpdate(models);
		return true;
	}

	@Override
	public boolean updateModel(Models models) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(models);
		return true;
	}

	@Override
	public boolean deleteModel(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Models models = session.get(Models.class, id);
		session.delete(models);
		return true;
	}

	@Override
	public Models getModel(Long id) {
		Session session = this.sessionFactory.openSession();
		Models models = session.get(Models.class, id);
		return models;
	}

	@Override
	public List<Models> getAllModel() {
		Session session = this.sessionFactory.openSession();
		List<Models> modelsList = session.createQuery("from Models").list();
		return modelsList;
	}

	@Override
	public boolean modelNameWithSameBrand(String name, Long brandId) {
		Session session = this.sessionFactory.openSession();
		Query query = session
				.createQuery("select count(*) from Models models where models.name=:name and models.brand.id=:id");
		query.setString("name", name);
		query.setLong("id", brandId);
		Long count = (Long) query.uniqueResult();
		return count > 0;
	}

	@Override
	public List<Models> getAllModelOfBrand(Long id) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Models models where models.brand.id=:id");
		query.setLong("id", id);
		List<Models> modelsList = query.list();
		return modelsList;
	}

}
