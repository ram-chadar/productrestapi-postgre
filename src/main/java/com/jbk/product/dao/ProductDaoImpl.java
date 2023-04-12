package com.jbk.product.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.product.entity.Product;
import com.jbk.product.sort.ProductIdComparator;
import com.jbk.product.sort.ProductNameComparator;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveProduct(Product product) {
		Session session = null;
		Transaction transaction = null;
		boolean isAdded = false;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Product prd = session.get(Product.class, product.getProductId());
			if (prd == null) {
				session.save(product);
				transaction.commit();
				isAdded = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();

			}
		}

		return isAdded;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			// criteria.addOrder(Order.asc("ProductId"));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Product getProductById(String productId) {
		Session session = null;
		Product product = null;
		try {
			session = sessionFactory.openSession();
			product = session.get(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean deleteProduct(String productId) {
		Session session = null;
		Transaction transaction = null; // save, update,delete
		boolean isDeleted = false;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				transaction.commit();
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = null;
		Transaction transaction = null; // save, update,delete
		boolean isUpdated = false;
		try {
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();
			Product prd = session.get(Product.class, product.getProductId());
			if (prd != null) {
				session.evict(prd);
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public int uploadProductList(List<Product> list) {
		Session session = null;
		Transaction transaction = null;
		int count = 0;
		try {
			
			
			for (Product product : list) {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();
				session.save(product);
				transaction.commit();
				count = count + 1;
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();

			}
		}

		return count;
	}

}
