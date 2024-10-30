package com.jsp.DAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.DTO.Hospital;

public class HospitalDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public  void createHospital() {
		Scanner sc = new Scanner(System.in);
		
		Hospital hospital = new Hospital();
		System.out.println("Enter hospital name..!");
		hospital.setHName(sc.nextLine());
		System.out.println("Enter hospital address...!");
		hospital.setHAddress(sc.nextLine());
		System.out.println("Enter hospital city...!");
		hospital.setHCity(sc.next());
		
		transaction.begin();
		manager.persist(hospital);
		transaction.commit();
		
		System.out.println("Hospital created successfully...!");
		
		
	}
	
	public void updateHospitalNameById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter id to update hospital name..!");
		int id = sc.nextInt();
		
		Hospital hospital = manager.find(Hospital.class, id);

		if(hospital != null) {
			
			System.out.println("Enter new hospital name..!");
			
			
			transaction.begin();
			hospital.setHName(sc.next());
			manager.merge(hospital);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Hospital name successfully updated...!");
		}else {
			System.out.println("Hospital does not Exixt....!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	public void removeHospitalById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter id to delete hospital..!");
		int id = sc.nextInt();
		
		Hospital hospital = manager.find(Hospital.class, id);
		if(hospital != null) {
			transaction.begin();
			manager.remove(hospital);
			transaction.commit();
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Hospital deleted successfulley....!");
		}else {
			System.out.println("Hospital is not found...!");
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	public void viewHospitalByName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter hospital name to display details of hospital..!");
		String name = sc.nextLine();
		
		Query q = manager.createQuery("select h from Hospital h where hname=?1 ");
		q.setParameter(1, name);
		List<Hospital> hospital = q.getResultList();
		for(Hospital h : hospital) {
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Hospital id is: " + h.getHos_id());
			System.out.println("Hospital name is: " + h.getHName());
			System.out.println("Hospital address is: " + h.getHAddress());
			System.out.println("Hospital city is: " + h.getHCity());
			
			 System.out.println("-------------------------------------------------");
		}
	}
	
	public void displayAllHospitalDetails() {
		
		Query q = manager.createQuery("select h from Hospital h");
		
		List<Hospital> list = q.getResultList();
		for(Hospital h : list) {
			
			 System.out.println("-------------------------------------------------");
			
			System.out.println("Hospital id is: " + h.getHos_id());
			System.out.println("Hospital name is: " + h.getHName());
			System.out.println("Hospital address is: " + h.getHAddress());
			System.out.println("Hospital city is: " + h.getHCity());
			
			 System.out.println("-------------------------------------------------");
		}
	}
	

}
