package model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



// All Date format has been changed to LocalDate formatting
@Entity
public class Patient {
	/**
	 * Encapsulated Fields.
	 * */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int patientId;
	@Column
	private String foreName;
	@Column
	private String surName;
	@Column
	private LocalDate dateOfBirth;
	@Column
	private boolean gender;
	@Column
	private String address;
	@Column
	private String phoneNumber;
	@Column
	private String nextOfKin;
	
	
	public Patient() {}


	public Patient(int patientId, String foreName, String surName, LocalDate dateOfBirth, boolean gender,
			String address, String phoneNumber, String nextOfKin) {
		super();
		this.patientId = patientId;
		this.foreName = foreName;
		this.surName = surName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.nextOfKin = nextOfKin;
	}


	public Patient(String foreName, String surName, LocalDate dateOfBirth, boolean gender, String address,
			String phoneNumber, String nextOfKin) {
		super();
		this.foreName = foreName;
		this.surName = surName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.nextOfKin = nextOfKin;
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public String getForeName() {
		return foreName;
	}


	public void setForeName(String foreName) {
		this.foreName = foreName;
	}


	public String getSurName() {
		return surName;
	}


	public void setSurName(String surName) {
		this.surName = surName;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public boolean isGender() {
		return gender;
	}


	public void setGender(boolean gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getNextOfKin() {
		return nextOfKin;
	}


	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((foreName == null) ? 0 : foreName.hashCode());
		result = prime * result + (gender ? 1231 : 1237);
		result = prime * result + ((nextOfKin == null) ? 0 : nextOfKin.hashCode());
		result = prime * result + patientId;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
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
		Patient other = (Patient) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (foreName == null) {
			if (other.foreName != null)
				return false;
		} else if (!foreName.equals(other.foreName))
			return false;
		if (gender != other.gender)
			return false;
		if (nextOfKin == null) {
			if (other.nextOfKin != null)
				return false;
		} else if (!nextOfKin.equals(other.nextOfKin))
			return false;
		if (patientId != other.patientId)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", foreName=" + foreName + ", surName=" + surName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", nextOfKin=" + nextOfKin + "]";
	}

	
}

