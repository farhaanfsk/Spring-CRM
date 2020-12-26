package service;

import java.util.List;

import entity.Customer;

public interface CustomerService {
	
	public List<Customer> listCustomer();

	public void addCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
}
