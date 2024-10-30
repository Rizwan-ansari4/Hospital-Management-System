package com.jsp.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.DTO.Hospital;
import com.jsp.DTO.Patient;

public class PatientDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public void createPatient(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter hospital ID to create patient:-");
		int id = sc.nextInt();
		
		Hospital hospital = manager.find(Hospital.class, id);
		
		if(hospital != null) {
			
			Patient patient = new Patient();
			
			System.out.println("Enter patient name:");
			patient.setPName(sc.next());
			System.out.println("Enter patient diagnosis:");
			patient.setPDiagnosis(sc.next());
			System.out.println("Enter patient address:");
			patient.setPAddress(sc.next());
			
			List<Patient> listPatient = new ArrayList<Patient>();
			listPatient.add(patient);
			
			transaction.begin();
			hospital.setPatient(listPatient);
			patient.setHospital(hospital);
			manager.persist(patient);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Patient successfully admitted.");
		}else {
			System.out.println("Hospital not found...");
			
			 System.out.println("-------------------------------------------------");
		}
		
	}
	
	public void removePatientById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter patient id to delete patient...!");
		int id = sc.nextInt();
		
		Patient patient = manager.find(Patient.class, id);
		
		if(patient != null) {
			transaction.begin();
			manager.remove(patient);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Patient record deleted successfully...!");
		}else {
			System.out.println("Patient is not found...!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	
	public void viewPatientDetail() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter patient name to display record...!");
		String name = sc.next();
		
		Query q = manager.createQuery("select p from Patient p where p.PName = ?1");
		q.setParameter(1, name);
		List<Patient> patients = q.getResultList();
		
		for(Patient p : patients) {
			Hospital h = p.getHospital();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Hospital id is: " + h.getHos_id()); 
			System.out.println("Hospital name is: " + h.getHName());
			System.out.println(p.getPat_id() + " " + p.getPName() + " " + p.getPDiagnosis() + " " + p.getPAddress());
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	
	public void viewAllPatient() {
		Query q = manager.createQuery("select p from Patient p");
		List<Patient> listPatient = q.getResultList();
		for(Patient p : listPatient) {
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Patient hospita id is: " + p.getHospital().getHos_id());
			System.out.println("Patient hospital name is: " + p.getHospital().getHName());
			
			System.out.println("Patient id is: " + p.getPat_id());
			System.out.println("Patient name is: " + p.getPName());
			System.out.println("Patient diagnosis is: " + p.getPDiagnosis());
			System.out.println("Patient address is: " + p.getPAddress());
			
			 System.out.println("-------------------------------------------------");
		}
	}

}
