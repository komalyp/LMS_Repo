package lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookorder")
public class BookOrder {
	
	public BookOrder() {
		
	}

	@Id
	@Column(name="orderid")
	private int orderid;
	
	 @ManyToOne
		@JoinColumn(name = "emailid")
	private User emailid;
	 
	 @ManyToOne
	 @JoinColumn(name = "bookid")
	private Book id;
	
	@Column(name = "orderdate")
	private Date orderDate;
	
	@Column(name = "returndate")
	private Date returnDate;
	
	@Column(name = "latereturnfine")
	private int lateReturnFee;
	
	@Column(name = "isbookreturned")
	private boolean isBookReturned;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public User getEmailid() {
		return emailid;
	}

	public void setEmailid(User emailid) {
		this.emailid = emailid;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getLateReturnFee() {
		return lateReturnFee;
	}

	public void setLateReturnFee(int lateReturnFee) {
		this.lateReturnFee = lateReturnFee;
	}

	public boolean isBookReturned() {
		return isBookReturned;
	}

	public void setBookReturned(boolean isBookReturned) {
		this.isBookReturned = isBookReturned;
	}

	public Book getId() {
		return id;
	}

	public void setId(Book id) {
		this.id = id;
	}

	public BookOrder(int orderid, User emailid, Book id, Date orderDate, Date returnDate, int lateReturnFee,
			boolean isBookReturned) {
		super();
		this.orderid = orderid;
		this.emailid = emailid;
		this.id = id;
		this.orderDate = orderDate;
		this.returnDate = returnDate;
		this.lateReturnFee = lateReturnFee;
		this.isBookReturned = isBookReturned;
	}

	



	
	
}

