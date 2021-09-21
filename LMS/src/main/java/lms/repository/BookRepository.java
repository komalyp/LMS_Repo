package lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import lms.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query(value = "SELECT * FROM book ORDER BY name",nativeQuery=true)
	List<Book> findBooks();
	
	@Query(value = "SELECT * FROM book where id=?",nativeQuery=true)
	Book getBookById(int id);
}
