package lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import lms.model.BookOrder;

public interface BookOrderRepository extends JpaRepository<BookOrder,Integer> {

	@Query(value="select * from bookOrder where orderid=?",nativeQuery=true)
	BookOrder findOrderById(int id);
	
	@Query(value="select bookid from bookOrder where orderid=?",nativeQuery=true)
	int findBookId(int orderId);
}
