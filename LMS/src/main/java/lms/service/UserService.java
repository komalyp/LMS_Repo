package lms.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.model.User;
import lms.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User userLogin(String emailId, String password) {
		//Validation part is pending
		System.out.println("inside login");
		User userFound = userRepo.findById(emailId).get();
		System.out.println("User logged in successfully!" + userFound.getFirstname());
		if(userFound != null && userFound.getPassword().equals(password)) {
			System.out.println("User logged in successfully!");
			return userFound;
			
		}else {
			return null;
		}
		
	}
	
	public List<User> getAllStudents(){
		return userRepo.getAllStudents();
	}
	
	public User registerStudent(User user) {
		
		return userRepo.save(user);
	}
}
