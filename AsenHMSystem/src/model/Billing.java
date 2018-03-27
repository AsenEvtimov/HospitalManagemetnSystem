package model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Billing {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	@Column
	private Calendar date;
	@Column
	private BigDecimal roomCharge;
	@Column
	private BigDecimal doctorsFee;
	@Column
	private String note;
	@Column
	private BigDecimal miscellaneousFee;
	@Column
	private Boolean payment;
	@ManyToOne
	@JoinColumn(name = "patientId", foreignKey = @ForeignKey(name = "BillingDeptId_FK"))
	private Patient patient;
	
	public Billing() {}

	public Billing(Integer id, Calendar date, BigDecimal roomCharge, BigDecimal doctorsFee, String note,
			BigDecimal miscellaneousFee, Boolean payment, Patient patient) {
		super();
		this.id = id;
		this.date = date;
		this.roomCharge = roomCharge;
		this.doctorsFee = doctorsFee;
		this.note = note;
		this.miscellaneousFee = miscellaneousFee;
		this.payment = payment;
		this.patient = patient;
	}

	public Billing(Calendar date, BigDecimal roomCharge, BigDecimal doctorsFee, String note,
			BigDecimal miscellaneousFee, Boolean payment, Patient patient) {
		super();
		this.date = date;
		this.roomCharge = roomCharge;
		this.doctorsFee = doctorsFee;
		this.note = note;
		this.miscellaneousFee = miscellaneousFee;
		this.payment = payment;
		this.patient = patient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public BigDecimal getRoomCharge() {
		return roomCharge;
	}

	public void setRoomCharge(BigDecimal roomCharge) {
		this.roomCharge = roomCharge;
	}

	public BigDecimal getDoctorsFee() {
		return doctorsFee;
	}

	public void setDoctorsFee(BigDecimal doctorsFee) {
		this.doctorsFee = doctorsFee;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getMiscellaneousFee() {
		return miscellaneousFee;
	}

	public void setMiscellaneousFee(BigDecimal miscellaneousFee) {
		this.miscellaneousFee = miscellaneousFee;
	}

	public Boolean getPayment() {
		return payment;
	}

	public void setPayment(Boolean payment) {
		this.payment = payment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((doctorsFee == null) ? 0 : doctorsFee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((miscellaneousFee == null) ? 0 : miscellaneousFee.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((roomCharge == null) ? 0 : roomCharge.hashCode());
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
		Billing other = (Billing) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (doctorsFee == null) {
			if (other.doctorsFee != null)
				return false;
		} else if (!doctorsFee.equals(other.doctorsFee))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (miscellaneousFee == null) {
			if (other.miscellaneousFee != null)
				return false;
		} else if (!miscellaneousFee.equals(other.miscellaneousFee))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (roomCharge == null) {
			if (other.roomCharge != null)
				return false;
		} else if (!roomCharge.equals(other.roomCharge))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Billing [id=" + id + ", date=" + date + ", roomCharge=" + roomCharge + ", doctorsFee=" + doctorsFee
				+ ", note=" + note + ", miscellaneousFee=" + miscellaneousFee + ", payment=" + payment + ", patient="
				+ patient + "]";
	}
	

}	