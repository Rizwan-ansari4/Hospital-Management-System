package com.jsp.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MedicalRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Precord_id;
	private String DateOfExamination;
	private String Problem;

	@ManyToOne
	Patient patient;

	public int getPrecord_id() {
		return Precord_id;
	}

	public void setPrecord_id(int precord_id) {
		Precord_id = precord_id;
	}

	public String getDateOfExamination() {
		return DateOfExamination;
	}

	public void setDateOfExamination(String dateOfExamination) {
		DateOfExamination = dateOfExamination;
	}

	public String getProblem() {
		return Problem;
	}

	public void setProblem(String problem) {
		Problem = problem;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
}
