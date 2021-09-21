package lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lms.model.Role;
import lms.model.User;
import lms.repository.UserRepository;
import lms.service.BookService;
import lms.service.UserService;
import utility.Const;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService usService;

	@Autowired
	BookService bookService;

	@Autowired
	UserRepository userRepo;

	@GetMapping("")
	public String viewHomePage() {
		return Const.HOME;
	}
	
	@RequestMapping(path ="/register",method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}

	@RequestMapping(path = "/saveUser",method = RequestMethod.POST)
	public String processRegister(@ModelAttribute("user") User user) {
		usService.saveUser(user);
		return Const.HOME;
	}
 
	@RequestMapping(path ="/showLogin",method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	@RequestMapping(path ="/userLogin",method = RequestMethod.POST)
	public String manageUserLogin(@ModelAttribute("user") User user) {
		String email = user.getEmailid();
		String pass = user.getPassword();
		
		User us = usService.userLogin(email, pass);
		if (us != null) {
		 int roleId=us.getRoleid();
			if (roleId!= 1) {// Admin
				return "/admin/adminHome.html";
			}
		}
	return "/studentHome";
	}
}