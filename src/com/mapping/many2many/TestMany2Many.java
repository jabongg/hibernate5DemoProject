package com.mapping.many2many;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.hibernate.HibernateUtil;


/**
 * @author jang
 */
public class TestMany2Many {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory("").openSession();
         transaction = session.getTransaction();
         transaction.begin();

         Address address1 = new Address("Varanasi", "UP", "India", "221110");
         Address address2 = new Address("Lucknow", "UP", "India", "120001");

         // Employee1 have 2 addresses
         Employee employee1 = new Employee("Ragini Singh", "Project Manager", 650000);
         employee1.getAddresses().add(address1);
         employee1.getAddresses().add(address2);

         // Employee2 have 1 address
         Employee employee2 = new Employee("Mahavir Sharma", "Software Tester", 750000);
         employee2.getAddresses().add(address1);

         session.save(employee1);
         session.save(employee2);
         transaction.commit();

         System.out.println("Records saved successfully");

      } catch (Exception e) {
         if (transaction != null) {
            System.out.println("Transaction is being rolled back.");
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }
      HibernateUtil.shutdown();
   }
}