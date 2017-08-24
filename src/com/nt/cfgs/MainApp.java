package com.nt.cfgs;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nt.cfgs.HibernateUtil;

public class MainApp {
	
	public SessionFactory getSessionFactory(String databaseName) {
		return HibernateUtil.getSessionFactory(databaseName);
	}	
	 public static void main(String[] args) {
		    Session session = HibernateUtil.getSessionFactory("SPADE_DB").openSession();
		    org.hibernate.Transaction tx = session.beginTransaction();

		    
			Employee emp1 = new Employee();
			emp1.setName("VIPUL");
			
			try {
				session.save(emp1);
			} catch (Exception e) {
				e.printStackTrace();
			}


			Employee emp2 = new Employee();
			emp2.setName("DLF");
			
			try {
				session.save(emp2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			System.out.println("Successfully inserted");

			
		    // Check database version
		    String sql = "select version()";

		    String result = (String) session.createNativeQuery(sql).getSingleResult();
		    System.out.println(result);

		    
		    
			CriteriaQuery<Employee> cq = session.getCriteriaBuilder().createQuery(Employee.class);
			cq.from(Employee.class);
			List<Employee> employeeList = session.createQuery(cq).getResultList();

			
			//System.out.println(employeeList);
			
			for (Employee employee : employeeList) {
				System.out.println("ID: " + employee.getId());
				System.out.println("Name: " + employee.getName());
			}  
			
			//session.delete(emp1);
			tx.commit();
		    session.close();

		    
		    HibernateUtil.shutdown();
		  }
}
