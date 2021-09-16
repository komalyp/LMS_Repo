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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import lms.model.Book;
import lms.repository.BookRepository;
import lms.service.BookService;
import utility.Constants;



@Controller
@RequestMapping(Constants.BOOKS)
public class BookController {

	@Autowired
	BookService bookService;
/*
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model)
	{
		
	}
	*/
	@GetMapping
	public String getAllBooks(Model model) {
		List<Book> list = bookService.loadAllBooks();
		model.addAttribute(Constants.ALL_BOOKS, list);
		return "index";
	}

	@RequestMapping("/new")
	public String showNewBookPage(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "add-book";
	}

	@RequestMapping("/search")
	public String showSearchBookPage(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		System.out.println("We are in search controller");
		return "search-book";
	}

	@RequestMapping(path = "/search1/{id}", method = RequestMethod.POST)
	//@ModelAttribute(Searched_Book)
	public String showsearchBook(@PathVariable("id") int id,@ModelAttribute Book book,Model model) {
		int id1 = book.getId();
		System.out.println("We are in search1 controller --id:"+id1);
		
		Book book1 = bookService.searchBook(id1);
		//System.out.println(book1.getId());
		if (book1 != null) {
			model.addAttribute("SearchedBooks",book1);
			return "book-found.html";
		} else {

			return "book-not-found.html";
		}
	}
	
	// todays task-Search and issue book
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String saveNewBook(@ModelAttribute("book") Book book) {
		bookService.saveBook(book);
		return "redirect:/books";
	}

	@GetMapping("/edit/{id}")
	private String editBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.loadBookById(id);
		model.addAttribute("book", book);
		return "edit-book";
	}

	@RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
	private String updateBook(@PathVariable("id") int id, @ModelAttribute Book book) {
		book.setId(id);
		bookService.updateBook(book);
		return "redirect:/books";
	}

	@GetMapping("/delete/{id}")
	private String deleteBook(@PathVariable("id") int id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
}
/*
 * @GetMapping(value = "/browsebooks") public String
 * browseBooks(@RequestParam(required = false) String title, Model model) {
 * List<Book> books; books = bookService.searchBook(title);
 * model.addAttribute("Searched Books", books); return
 * "books/user-browse-books.html"; }
 */
