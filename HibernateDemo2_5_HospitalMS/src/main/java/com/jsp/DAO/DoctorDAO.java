package com.jsp.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.DTO.Doctor;
import com.jsp.DTO.Hospital;

public class DoctorDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	Scanner sc = new Scanner(System.in);
	
	public void createDoctor() {
		
		System.out.println("Enter hospital id to create doctor...!");
		int id = sc.nextInt();
		
		Hospital hospital = manager.find(Hospital.class, id);
		
		if(hospital != null) {
			Doctor doctor = new Doctor();
			
			System.out.println("Enter doctor name..");
			String name = sc.next();
			doctor.setDName(name);
			System.out.println("Enter doctor qualification");
			String qualification = sc.next();
			doctor.setQualification(qualification);
			System.out.println("Enter doctor salary");
			Double sal = sc.nextDouble();
			doctor.setSalary(sal);
			
			List<Doctor> listDoctor = new ArrayList<Doctor>();
			listDoctor.add(doctor);
			
			transaction.begin();
			hospital.setDoctors(listDoctor);
			doctor.setHospital(hospital);
			manager.persist(hospital);
			manager.persist(doctor);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Successfully doctor is created...!");
		}else {
			System.out.println("Hospital does not exist..!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	
	public void updateDoctorSalBasedOnId(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Doctor Id");
		int id = sc.nextInt();
		Doctor doctor = manager.find(Doctor.class,id);
		if(doctor != null){
			System.out.println("Enter New Salaray To Update");
			double sal = sc.nextDouble();
			doctor.setSalary(sal);
			transaction.begin();
			manager.merge(doctor);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Doctor salary has been successfully updated...!");
		}
		else{
			System.out.println("Doctor is not found");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	
	public void removeDoctor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter doctor id to delete ..!");
		int id = sc.nextInt();
		
		Doctor doctor = manager.find(Doctor.class, id);
		if(doctor != null) {
			transaction.begin();
			manager.remove(doctor);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			 
			System.out.println("Doctor deleted successfulley....!");
		}else {
			System.out.println("Doctor is not found...!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	public void doctorDetailsbasedOnName()	{
		
		Query q=manager.createQuery("select  d from Doctor d where d.DName=?1");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Name");
		String name=sc.next();
		q.setParameter(1,name);
		List<Doctor> listDoctors=q.getResultList();
			for(Doctor i:listDoctors){
				System.out.println("----------------------------");
				System.out.println("Hospital Id : "+i.getHospital().getHos_id());
				System.out.println("Hospital Name : "+i.getHospital().getHName());
				System.out.println("Doctor Id : "+i.getDoc_id());
				System.out.println("Doctor name : "+i.getDName());
				System.out.println("Doctor Qualification :"+i.getQualification());
				System.out.println("Doctor Salary : "+i.getSalary());
				
				System.out.println("----------------------------------------");
			}
		
	}
	
	
	
	public void displayAlldoctorDetails(){
		
		Query q = manager.createQuery("select  d from Doctor d");
		List<Doctor> listDoctors = q.getResultList();
			for(Doctor i:listDoctors){
				System.out.println("----------------------------");
				System.out.println("Hospital Id : "+i.getHospital().getHos_id());
				System.out.println("Hospital Name : "+i.getHospital().getHName());
				System.out.println("Doctor Id : "+i.getDoc_id());
				System.out.println("Doctor name : "+i.getDName());
				System.out.println("Doctor Qualification :"+i.getQualification());
				System.out.println("Doctor Salary : "+i.getSalary());
				
				System.out.println("----------------------------------------");
			}
		
	}
	

}
