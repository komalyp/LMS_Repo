package lms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.model.Role;
import lms.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	@Autowired
	RoleRepository roleRepo;

	public Role getRoleByUserType(String name) {

		return roleRepo.getRole(name);
	}
}
