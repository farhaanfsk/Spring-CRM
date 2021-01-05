package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entity.Customer;
import entity.CustomerDetail;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory factory;

	public List<Customer> listCustomer() {
		Session session = factory.getCurrentSession();

		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		return query.getResultList();
	}

	public void addCustomer(Customer customer) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	public Customer getCustomer(int id) {
		Session session = factory.getCurrentSession();

		return session.get(Customer.class, id);
	}

	public void deleteCustomer(int id) {
		Session session = factory.getCurrentSession();
		session.delete(session.get(Customer.class, id));
	}

	@Override
	public CustomerDetail getCustomerDetail(Customer c) {
		Session session = factory.getCurrentSession();
		Query<CustomerDetail> query = session.createQuery("from CustomerDetail where customer_id=:id",
				CustomerDetail.class);
		query.setParameter("id", c.getId());

		try {
			CustomerDetail customerDetail = query.getSingleResult();
			System.out.println(customerDetail.toString());
			return customerDetail;
		} catch (NoResultException nre) {
			return new CustomerDetail();
		}

	}

	@Override
	public void addCustomerDetail(CustomerDetail customerDetail) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(customerDetail);
	}

}
