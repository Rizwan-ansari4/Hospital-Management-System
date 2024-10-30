package com.jsp.CONTROLLER;

import java.util.Scanner;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import com.jsp.DAO.DoctorDAO;
import com.jsp.DAO.HospitalDAO;
import com.jsp.DAO.MedicalRecordDAO;
import com.jsp.DAO.PatientDAO;


public class MainHospital {
	
	public static boolean getExit(){
		System.out.println("Thank you visit again...");
		return false;
	}
	
	public static void main(String[] args) {
		
		HospitalDAO hospital = new HospitalDAO();
		DoctorDAO doctor = new DoctorDAO();
		PatientDAO patient = new PatientDAO();
		MedicalRecordDAO medicalRecord = new MedicalRecordDAO();
		
		
		Scanner sc = new Scanner(System.in);
				
				System.out.println("Welcome To My Application...");
				
				while (true) {
					System.out.println("============================================");
					System.out.println("1) For Create Hospital...");
					System.out.println("2) For Assign Doctor Based On Hospital Id..!");
					System.out.println("3) For Assign Patient Based on Hospita Id...!");
					System.out.println("4) For Enter Medical Record Based On Patient Id...!");
					System.out.println("5) For Exit...!");
					System.out.println("============================================");
					switch (sc.nextInt()) {
					case 1:
						System.out.println("============================================");
						System.out.println("Enter 1 for create Hospital...!");
						System.out.println("Enter 2 for update hospital...!");
						System.out.println("Enter 3 for remove Hospital...!");
						System.out.println("Enter 4 for view Hospital detail based on name..!");
						System.out.println("Enter 5 for view all hospital...!");
						System.out.println("============================================");
						switch(sc.nextInt()) {
							case 1: hospital.createHospital();
								break;
							case 2 : hospital.updateHospitalNameById();
								break;
							case 3 : hospital.removeHospitalById();
								break;
							case 4 : hospital.viewHospitalByName();
								break;
							case 5 : hospital.displayAllHospitalDetails();
								break;
							}
							
					case 2:
						System.out.println("============================================");
						System.out.println("Enter 1 for create doctor....!");
						System.out.println("Enter 2 for update doctor...!");
						System.out.println("Enter 3 for remove doctor...!");
						System.out.println("Enter 4 for view doctor detail based on name...!");
						System.out.println("Enter 5 for display all doctor...!");
						System.out.println("============================================");
						switch(sc.nextInt()) {
							case 1 : doctor.createDoctor();
								break;
							case 2 : doctor.updateDoctorSalBasedOnId();
								break;
							case 3 : doctor.removeDoctor();
								break;
							case 4 : doctor.doctorDetailsbasedOnName();
								break;
							case 5 : doctor.displayAlldoctorDetails();
								break;
							}
					
					case 3:
						
						break;
					case 4 :
						break;
						
					case 5:
						getExit();
						return;
					default:System.out.println("Invalid Option...");
					}
				}
		
		
		
	}

}
