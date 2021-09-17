package lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {

	@Id
	@Column(name = "Roleid")
	private int roleid;

	@Column(name = "Name")
	private String name;

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleId) {
		this.roleid = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role() {

	}

	public Role(int roleid, String name) {
		super();
		this.roleid = roleid;
		this.name = name;
	}

}
