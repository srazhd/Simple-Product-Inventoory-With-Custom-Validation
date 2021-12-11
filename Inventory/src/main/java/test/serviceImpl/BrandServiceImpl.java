package test.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.model.Brand;
import test.service.BrandService;

@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {
	@Autowired
	private SessionFactory sessionFactory;

//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	@Override
	public boolean saveBrand(Brand brand) {
		Date date = new Date(System.currentTimeMillis());
		brand.setEntryDate(date);
		Session session = this.sessionFactory.openSession();

		session.saveOrUpdate(brand);
		return true;
	}

	@Override
	public boolean updateBrand(Brand brand) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(brand);
		return true;
	}

	@Override
	public boolean deleteBrand(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Brand brand = session.get(Brand.class, id);
		session.delete(brand);
		return true;
	}

	@Override
	public boolean brandNameIsExist(String name) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("select count(*) from Brand brand where brand.name=:name ");
		query.setString("name", name);
		Long count = (Long) query.uniqueResult();
		return count > 0;
	}

	@Override
	public Brand getBrand(Long id) {
		Session session = this.sessionFactory.openSession();
		Brand brand = session.get(Brand.class, id);
		return brand;
	}

	@Override
	public List<Brand> getAllBrand() {
		Session session = this.sessionFactory.openSession();
		List<Brand> brandList = session.createQuery("from Brand").list();
		return brandList;
	}

}
