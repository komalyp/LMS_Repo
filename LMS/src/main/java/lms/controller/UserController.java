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
		System.out.println("We are in register");
		User user = new User();
		model.addAttribute("user", user);
		System.out.println("We are in register after user line");
		return "signup";
	}

	@PostMapping("/saveUser")
	public String processRegister(@ModelAttribute("user") User user) {
		userRepo.save(user);
		return "users";
	}
 
	@PostMapping("/login")
	public String manageUserLogin(@ModelAttribute("user") User user) {
		String email = user.getEmailid();
		String pass = user.getPassword();

		User us = usService.userLogin(email, pass);
		if (us != null) {
			Role role = us.getRoleid();
			if (role.getRoleid() != 1) {// Admin
				return "home";
			}
		}
		return "user";
	}
}