package dao;

import java.util.List;

import entity.Customer;

public interface CustomerDao {
	
	public List<Customer> listCustomer();

	public void addCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
}
