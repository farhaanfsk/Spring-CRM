package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory factory;

	@Transactional
	public List<Customer> listCustomer() {
		Session session = factory.getCurrentSession();

		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		return query.getResultList();
	}

}
