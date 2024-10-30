package com.jsp.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ManyToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.service.spi.Manageable;

import com.jsp.DTO.MedicalRecord;
import com.jsp.DTO.Patient;

public class MedicalRecordDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public void addMedicalRecord() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter patient id to record medical:");
		int id = sc.nextInt();
		
		Patient patient = manager.find(Patient.class, id);
		MedicalRecord medicalRecord = new MedicalRecord();
		
		if(patient != null) {
			System.out.println("Enter patient date of examination:");
			medicalRecord.setDateOfExamination(sc.next());
			System.out.println("Enter patient problem:");
			medicalRecord.setProblem(sc.next());
			
			List<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
			listMedicalRecord.add(medicalRecord);
			
			transaction.begin();
			patient.setMedicalRecord(listMedicalRecord);
			medicalRecord.setPatient(patient);
			manager.persist(medicalRecord);
			manager.persist(patient);

			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Patient medical record successfully recorded...!");
		}else {
			System.out.println("Patient record not found...!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	
	public void updateDateOfExaminById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Patient Id to found the patient.!");
		int id = sc.nextInt();
		Patient patient = manager.find(Patient.class,id);
		if(patient != null) {
			System.out.println("Enter Precord_id to update the date of examination...!");
			int medical_id = sc.nextInt();
			MedicalRecord m = manager.find(MedicalRecord.class,medical_id);
			System.out.println("Enter new date of examination of patient:");
			String date = sc.next();
			
			m.setDateOfExamination(date);
			transaction.begin();
			manager.merge(m);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Date of examination of patient is successfull updated...!");
		}else {
			System.out.println("The patient id for " + id + " is not successfully found....!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	
	public void removeMedicalRecordBasedOnPrecordId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Precord Id to delete medical record...!:");
		int id = sc.nextInt();
		
		MedicalRecord m = manager.find(MedicalRecord.class,id);
		
		if(m != null){
			
			transaction.begin();
			manager.remove(m);
//			manager.merge(m);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Patient Precord detail successfully deleted....!");
		}else {
			System.out.println("Patient precord id for " + id + " is not found...!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	
	
	public void viewMedicalRecordBasedOnId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the patient Id To Get Data");
		int id = sc.nextInt();
		
		Query q = manager.createQuery("select m from MedicalRecord m where m.Precord_id=?1");
		
		q.setParameter(1,id);
		 List<MedicalRecord> listMedicalRecord	= q.getResultList();
		 for(MedicalRecord i:listMedicalRecord){
			 System.out.println("-------------------------------------------------");
			 System.out.println("Record Id: " + i.getPrecord_id());
			 System.out.println("Record Problem: " + i.getProblem());
			 System.out.println("Record Date Of Examination: " + i.getDateOfExamination());
			 System.out.println("Patient Id : " + i.getPatient().getPat_id());
			 System.out.println("Patient Name: " + i.getPatient().getPName());
			 System.out.println("-------------------------------------------------");
		 }
	}
	

}
