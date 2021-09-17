package lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lms.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value = "SELECT * from role where name=?", nativeQuery = true)
	Role getRole(String userType);
}
