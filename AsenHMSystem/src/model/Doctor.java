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
public class Doctor extends Employee {
	
	@Column
	private String qualifications;
	@ManyToOne
	@JoinColumn(name = "deptId", foreignKey = @ForeignKey(name = "DoctorDeptId_FK"))
	private Department department;
	
	public Doctor() {}

	public Doctor(int empId, String firstName, String lastName, boolean gender, 
			String address, String phoneNumber, String qualifications, Department department) {
		super(empId, firstName, lastName, gender, address, phoneNumber);
		this.qualifications = qualifications;
		this.department = department;
	}

	public Doctor(String firstName, String lastName, boolean gender,
			String address, String phoneNumber, String qualifications, Department department) {
		super(firstName, lastName, gender, address, phoneNumber);
		this.qualifications = qualifications;
		this.department = department;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
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
		int result = super.hashCode();
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((qualifications == null) ? 0 : qualifications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (qualifications == null) {
			if (other.qualifications != null)
				return false;
		} else if (!qualifications.equals(other.qualifications))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doctor [qualifications=" + qualifications + ", department=" + department + ", isGender()=" + isGender()
				+ ", toString()=" + super.toString() + "]";
	}

}
