package com.jsp.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Doc_id;
	private String DName;
	private String Qualification;
	private double Salary;
	
	@ManyToOne
	Hospital hospital;

	public int getDoc_id() {
		return Doc_id;
	}

	public void setDoc_id(int doc_id) {
		Doc_id = doc_id;
	}

	public String getDName() {
		return DName;
	}

	public void setDName(String dName) {
		DName = dName;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public double getSalary() {
		return Salary;
	}

	public void setSalary(double salary) {
		Salary = salary;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	
	
	

}
