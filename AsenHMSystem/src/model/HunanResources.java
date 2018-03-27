package model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HunanResources extends Employee {

	@Column
	private String qualifications;
	
	public HunanResources(){}
	
	public HunanResources(int empId, String firstName, String lastName, boolean gender, 
			String address, String phoneNumber, String qualifications, Department department) {
		super(empId, firstName, lastName, gender, address, phoneNumber);
		this.qualifications = qualifications;
		
	}

	public HunanResources(String firstName, String lastName, boolean gender,
			String address, String phoneNumber, String qualifications, Department department) {
		super(firstName, lastName, gender, address, phoneNumber);
		this.qualifications = qualifications;
		
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		HunanResources other = (HunanResources) obj;
		if (qualifications == null) {
			if (other.qualifications != null)
				return false;
		} else if (!qualifications.equals(other.qualifications))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HunanResources [qualifications=" + qualifications + ", getEmpId()=" + getEmpId() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", isGender()=" + isGender() + ", getAddress()="
				+ getAddress() + ", getPhoneNumber()=" + getPhoneNumber() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}

	
}
