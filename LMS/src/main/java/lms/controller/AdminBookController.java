package lms.controller;

import java.util.List;
//import utility.Constants;
//import java.util.Optional;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lms.model.Book;
import lms.model.BookOrder;
import lms.repository.BookOrderRepository;
import lms.service.BookService;
import utility.Const;

@Controller
@RequestMapping(Const.ADMIN)
public class AdminBookController {

	@Autowired
	BookService bookService;

	@Autowired
	BookOrderRepository bookOrderRepo;
	/*
	 * @RequestMapping(method = RequestMethod.GET) public String login(Model model)
	 * {
	 * 
	 * }
	 */
	

	@GetMapping
	public String viewHome(Model model) {
		List<Book> list = bookService.loadAllBooks();
		model.addAttribute("Books", list);
		return "/admin/adminHome";
	}
	
	@GetMapping(path="/viewBook")
	public String viewBooksByAdmin(Model model) {
		List<Book> list = bookService.loadAllBooks();
		model.addAttribute("adminBooks", list);
		return "/admin/adminviewBooks";
	}
	
/*	@GetMapping(path="/viewBookByUser")
	public String viewBooks(Model model) {
		List<Book> list = bookService.loadAllBooks();
		model.addAttribute("studentViewBook", list);
		
		return "studentHome";
	}
*/
	@RequestMapping(Const.NEW_BOOK)
	public String showNewBookPage(Model model) {
		Book book = new Book();
		model.addAttribute(Const.BOOK, book);
		return Const.ADD_BOOK;
	}

	@PostMapping(Const.SAVE)
	public String saveNewBook(@ModelAttribute(Const.BOOK) Book book) {
		bookService.saveBook(book);
		return "/admin/adminHome";
	}

	@RequestMapping(Const.ADMIN_SEARCH_FORM )
	public String showSearchBookPage(Model model) {
		Book book = new Book();
		System.out.println("We are in Search book form");
		model.addAttribute(Const.BOOK, book);
		return Const.ADMIN_SEARCH;
	}

	@PostMapping(Const.SEARCH_ID)
	// @ModelAttribute(Searched_Book)
	public String showsearchBook(@PathVariable("id") int id, @ModelAttribute Book book, Model model) {
		int id1 = book.getId();
		Book book1 = bookService.searchBook(id1);
		// System.out.println(book1.getId());
		if (book1 != null) {
			model.addAttribute(Const.SEARCHED_BOOK, book1);
			return Const.BOOK_FOUND;
		} else {

			return Const.BOOK__NOT_FOUND;
		}
	}

	// todays task-Search and issue book

	@GetMapping(Const.EDIT)
	private String editBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.loadBookById(id);
		model.addAttribute(Const.BOOK, book);
		return Const.EDIT_BOOK;
	}

	@RequestMapping(Const.UPDATE_ON_ID)
	private String updateBook(@PathVariable("id") int id, @ModelAttribute Book book) {
		book.setId(id);
		bookService.updateBook(book);
		return "/admin/adminHome";
	}

	@GetMapping(Const.DELETE)
	private String deleteBook(@PathVariable("id") int id) {
		bookService.deleteBook(id);
		return "/admin/adminHome";
	}
	
/*	@GetMapping(path="/allOrders")
	private String viewAllOrders(Model model) {
		List<BookOrder> orders= bookOrderRepo.findAll();
		model.addAttribute("orderdBooks",orders);
		return "home";
	}
	*/

}