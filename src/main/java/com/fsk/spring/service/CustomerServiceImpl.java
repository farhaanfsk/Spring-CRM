package com.fsk.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsk.spring.dao.CustomerDao;
import com.fsk.spring.entity.Customer;
import com.fsk.spring.entity.CustomerDetail;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Transactional
	public List<Customer> listCustomer() {

		return customerDao.listCustomer();
	}

	@Transactional
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}

	@Transactional
	public Customer getCustomer(int id) {
		return customerDao.getCustomer(id);
	}

	@Transactional
	public void deleteCustomer(int id) {
		customerDao.deleteCustomer(id);

	}

	@Override
	@Transactional
	public CustomerDetail getCustomerDetail(Customer c) {
		return customerDao.getCustomerDetail(c);
	}

	@Override
	@Transactional
	public void addCustomerDetail(CustomerDetail customer) {
		customerDao.addCustomerDetail(customer);

	}

}
