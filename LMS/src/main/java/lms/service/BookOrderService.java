package lms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.model.BookOrder;
import lms.model.User;
import lms.repository.BookOrderRepository;

@Service
@Transactional
public class BookOrderService {

	@Autowired
	BookOrderRepository bookOrderRepo;
	
	
	public BookOrder placeOrder(BookOrder bookOrder) {
		System.out.println("inside service ");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(bookOrder.getOrderDate()); // Using order date
		c.add(Calendar.DATE, 15); // Adding 15 days
		//String output = sdf.format(c.getTime());
		bookOrder.setReturnDate(c.getTime());
		
		
		//final long days = ChronoUnit.DAYS.between(new Date(), bookOrder.getReturnDate());
//		long diff =new Date().getTime() - bookOrder.getReturnDate().getTime();
//		int days = (int)(diff / (1000*60*60*24));
//		if(days>0) {
//			int fine= days*5;
//			bookOrder.setLateReturnFee(fine);
//		}
		bookOrder.setLateReturnFee(0);
		bookOrder.setBookReturned(false);
		
		System.out.println("going out of service ");
		return bookOrderRepo.save(bookOrder);
	}
	
	public BookOrder returnBook(BookOrder bookOrder) {
		long diff =new Date().getTime() - bookOrder.getReturnDate().getTime();
		int days = (int)(diff / (1000*60*60*24));
		if(days>0) {
			int fine= days*5;
			bookOrder.setLateReturnFee(fine);
		}else {
			bookOrder.setLateReturnFee(0);
		}
		bookOrder.setBookReturned(true);
		return bookOrderRepo.save(bookOrder);
	}
	
	public BookOrder searchOrder(int id) {
		return bookOrderRepo.findOrderById(id);
	}
	
	public int findBookIdByOrder(BookOrder bookOrder) {
		return bookOrderRepo.findBookId(bookOrder.getOrderid());
	}
	
	/*public String currentUserEmail(User emailId,BookOrder bookOrder)
	{   bookOrder.getEmailid(emailId);
		return bookOrderRepo.save(bookOrder);
	}*/
	public void viewAllOrders() {
		System.out.println("---BOOKS---");
		List<BookOrder> orders= bookOrderRepo.findAll();
		for(int i=0;i<orders.size();i++) {
			System.out.println("Book id:"+ orders.get(i).getId());
			System.out.println("Order is issued by:"+ orders.get(i).getEmailid());
			System.out.println("Order issue date:"+ orders.get(i).getOrderDate());
			System.out.println("Order return date:"+ orders.get(i).getReturnDate());
			System.out.println("Order fine:"+ orders.get(i).getLateReturnFee());
			System.out.println("Is Order returned:"+ orders.get(i).isBookReturned());
			System.out.println("-----------------------------------------------");
		}
	}
}
