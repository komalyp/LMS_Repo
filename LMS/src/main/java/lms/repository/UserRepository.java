package lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import lms.model.Book; //not required
import lms.model.User;


public interface UserRepository extends JpaRepository<User,String> {

	@Query(value = "SELECT * FROM user where emailid=?",nativeQuery=true)
	User findByEmailId(String emailid);
	
	@Query(value = "SELECT * FROM user where roleid=1",nativeQuery=true)
	List<User> getAllStudents();
}
