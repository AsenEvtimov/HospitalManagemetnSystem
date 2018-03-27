package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ward {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int wardId;
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name = "deptId", foreignKey = @ForeignKey(name = "WardDeptId_FK"))
	private Department department;
	
	public Ward() {}

	public Ward(int wardId, String name, Department department) {
		super();
		this.wardId = wardId;
		this.name = name;
		this.department = department;
	}

	public Ward(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + wardId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ward other = (Ward) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (wardId != other.wardId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ward [wardId=" + wardId + ", name=" + name + ", department=" + department + "]";
	}
	
	
	
}
