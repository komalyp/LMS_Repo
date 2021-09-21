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
@RequestMapping(Const.USER)
public class UserBookController {

	@Autowired
	BookService bookService;

	@Autowired
	BookOrderRepository bookOrderRepo;
	
	
	
	@GetMapping(path="/viewBookByUser")
	public String viewBooks(Model model) {
		List<Book> list = bookService.loadAllBooks();
		model.addAttribute("studentViewBook", list);
		
		return "studentHome";
	}
	@RequestMapping(Const.USER_SEARCH_FORM )
	public String showSearchBookPage(Model model) {
		Book book = new Book();
		System.out.println("We are in Search book form");
		model.addAttribute(Const.BOOK, book);
		return Const.USER_SEARCH;
	}

	@PostMapping(Const.SEARCH_ID)
	// @ModelAttribute(Searched_Book)
	public String showsearchBook(@PathVariable("id") int id, @ModelAttribute Book book, Model model) {
		int id1 = book.getId();
		Book book1 = bookService.searchBook(id1);
		// System.out.println(book1.getId());
		if (book1 != null) {
			model.addAttribute(Const.SEARCHED_BOOK, book1);
			return Const.USER_BOOK_FOUND;
		} else {

			return Const.USER_BOOK__NOT_FOUND;
		}
	}

	

}