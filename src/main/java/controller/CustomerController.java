package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CustomerDao;
import entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	public CustomerDao customer;

	@RequestMapping("/list")
	public String listCustomer(Model model) {
		List<Customer> customers = customer.listCustomer();
		System.out.println(customers);
		model.addAttribute("customer", customers);
		return "customer-list";
	}
}
