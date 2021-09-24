package lms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lms.model.Book;
import lms.model.BookOrder;
import lms.model.Role;
import lms.model.User;
import lms.repository.UserRepository;
import lms.service.BookOrderService;
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
	BookOrderService bookOrderService;

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
	public String manageUserLogin(@ModelAttribute("user") User user,@ModelAttribute BookOrder bookOrder,Model model) {
		String email = user.getEmailid();
		String pass = user.getPassword();
		
		User us = usService.userLogin(email, pass);
		System.out.println("useremailid:"+us.getEmailid());
		if (us != null) {
		 int roleId=us.getRoleid();
			if (roleId!= 1) {// Admin
				model.addAttribute("AdminUser",us);
				return "/admin/adminHome.html";
			}
			else {
				bookOrder.setEmailid(user);
				model.addAttribute("StudnetUser",us);
				return "/student/studentHome.html";
			}
		}
		return Const.HOME;
	
	}
	
	@RequestMapping("/orderBook")
	public String orderBookForm(Model model) {
		Book book = new Book();
		System.out.println("We are in Order book form");
		model.addAttribute("book", book);
		return "placeOrder";
	}

	@RequestMapping(path = "/order1/{id}", method = RequestMethod.POST)
	// @ModelAttribute(Searched_Book)
	public String orderBook(@PathVariable("id") int id, Model model,@ModelAttribute("User") User user, @ModelAttribute Book book,
			@ModelAttribute BookOrder bookOrder) {
		User user1= bookOrder.getEmailid();
		System.out.println("In OrderBook");
		int id1 = book.getId();
		System.out.println("Book_id:"+id1);
		Book searchBook = bookService.searchBook(id1);
		
		System.out.println("We are in Order1 ** form");
		System.out.println("Searched Book"+searchBook.getBookname());
		if (searchBook != null) {
			model.addAttribute("Orders", searchBook);
			bookOrder.setId(searchBook);
			bookOrder.setEmailid(user1);
			System.out.println("useremailid:"+user.getEmailid());
			bookOrder.setOrderDate(new Date());
			if (bookOrderService.placeOrder(bookOrder) != null) {
				searchBook.setQuantity(searchBook.getQuantity() - 1);
				bookService.updateBook(searchBook);
				System.out.println("Order placed successfully!");
				return "student/Success";
			}
		}

		return "student/OrderBook_Rejected";
		// return "/student/studentHome";
	}
}