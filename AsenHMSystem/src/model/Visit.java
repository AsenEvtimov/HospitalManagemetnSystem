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

@Entity
public class Visit {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int visitId;
	@Column
	private boolean isOutpatient; // This is a marker that will be used to check whether the patient is an outpatient - certain parameters may apply.
	@Column
	private LocalDate admissionDate;
	@Column
	private LocalDate dischargeDate;
	@Column
	private LocalDate appointmentDate;
	@OneToOne
	@JoinColumn(name = "bedId", foreignKey = @ForeignKey(name = "VisitBedId_FK"))
	private Bed bed;
	@ManyToOne
	@JoinColumn(name = "doctorId", foreignKey = @ForeignKey(name = "VisitDoctorId_FK"))	
	private Doctor doctor;
	@ManyToOne
	@JoinColumn(name = "patientId", foreignKey = @ForeignKey(name = "VisitPatientId_FK"))	
	private Patient patient;
	
	public Visit () {}

	public Visit(boolean isOutpatient, LocalDate admissionDate, LocalDate dischargeDate, LocalDate appointmentDate,
			Bed bed, Doctor doctor, Patient patient) {
		super();
		this.isOutpatient = isOutpatient;
		this.admissionDate = admissionDate;
		this.dischargeDate = dischargeDate;
		this.appointmentDate = appointmentDate;
		this.bed = bed;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Visit(int visitId, boolean isOutpatient, LocalDate admissionDate, LocalDate dischargeDate,
			LocalDate appointmentDate, Bed bed, Doctor doctor, Patient patient) {
		super();
		this.visitId = visitId;
		this.isOutpatient = isOutpatient;
		this.admissionDate = admissionDate;
		this.dischargeDate = dischargeDate;
		this.appointmentDate = appointmentDate;
		this.bed = bed;
		this.doctor = doctor;
		this.patient = patient;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public boolean isIsOutpatient() {
		return isOutpatient;
	}

	public void setIsOutpatient(boolean isOutpatient) {
		this.isOutpatient = isOutpatient;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public LocalDate getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Bed getBed() {
		return bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
		result = prime * result + ((admissionDate == null) ? 0 : admissionDate.hashCode());
		result = prime * result + ((appointmentDate == null) ? 0 : appointmentDate.hashCode());
		result = prime * result + ((bed == null) ? 0 : bed.hashCode());
		result = prime * result + ((dischargeDate == null) ? 0 : dischargeDate.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + (isOutpatient ? 1231 : 1237);
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + visitId;
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
		Visit other = (Visit) obj;
		if (admissionDate == null) {
			if (other.admissionDate != null)
				return false;
		} else if (!admissionDate.equals(other.admissionDate))
			return false;
		if (appointmentDate == null) {
			if (other.appointmentDate != null)
				return false;
		} else if (!appointmentDate.equals(other.appointmentDate))
			return false;
		if (bed == null) {
			if (other.bed != null)
				return false;
		} else if (!bed.equals(other.bed))
			return false;
		if (dischargeDate == null) {
			if (other.dischargeDate != null)
				return false;
		} else if (!dischargeDate.equals(other.dischargeDate))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (isOutpatient != other.isOutpatient)
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (visitId != other.visitId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visit [\nvisitId=" + visitId + "\n isOutpatient=" + isOutpatient + "\n admissionDate=" + admissionDate
				+ "\n dischargeDate=" + dischargeDate + "\n appointmentDate=" + appointmentDate + "\n bed=" + bed
				+ "\n doctor=" + doctor + "\n patient=" + patient + "]";
	}

}
