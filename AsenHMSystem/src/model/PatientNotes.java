package model;

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
public class PatientNotes {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private Date date;
	@Column
	private String notes;
	@ManyToOne
	@JoinColumn(name = "patientId", foreignKey = @ForeignKey(name = "PatientNotesPatientId_FK"))
	private Patient patient;
	
	public PatientNotes(int id, Date date, String notes, Patient patient) {
		super();
		this.id = id;
		this.date = date;
		this.notes = notes;
		this.patient = patient;
	}
	public PatientNotes(Date date, String notes, Patient patient) {
		super();
		this.date = date;
		this.notes = notes;
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
		result = prime * result + id;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
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
		PatientNotes other = (PatientNotes) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PatientNotes [id=" + id + ", date=" + date + ", notes=" + notes + ", patient=" + patient + "]";
	}
	

}
