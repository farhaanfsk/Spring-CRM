package com.fsk.spring.dao;

import java.util.List;

import com.fsk.spring.entity.Customer;
import com.fsk.spring.entity.CustomerDetail;

public interface CustomerDao {
	
	public List<Customer> listCustomer();

	public void addCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public CustomerDetail getCustomerDetail(Customer c);

	public void addCustomerDetail(CustomerDetail customerDetail);
}
