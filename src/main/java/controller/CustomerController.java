package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Customer;
import service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	public CustomerService customerService;

	@GetMapping("/list")
	public String listCustomer(Model model) {
		List<Customer> customers = customerService.listCustomer();
		System.out.println(customers);
		model.addAttribute("customer", customers);
		return "customer-list";
	}

	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		Customer c = new Customer();
		model.addAttribute("customer", c);
		return "add-Customer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.addCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/updateForm")
	public String updateCustomer(@RequestParam("customerId") int id, Model model) {
		Customer c = customerService.getCustomer(id);
		model.addAttribute("customer", c);
		return "add-Customer";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
}
