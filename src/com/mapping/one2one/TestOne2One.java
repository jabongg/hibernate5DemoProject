package com.mapping.one2one;

import java.time.LocalDate;
import java.time.Month;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.hibernate.HibernateUtil;

/**
 * @author jang
 */
public class TestOne2One {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory("SPADE_DB").openSession();
         transaction = session.getTransaction();
         transaction.begin();

         User user = new User();
         user.setUsername("Xuang");
         user.setPassword("xuang@xyz");

         UserDetail userDetail = new UserDetail();
         userDetail.setFirstName("Mike");
         userDetail.setLastName("Jone");
         userDetail.setEmail("mike.jone@example.com");
         userDetail.setDob(LocalDate.of(1995, Month.APRIL, 1));

         user.setUserDetail(userDetail);

         session.persist(user);
         transaction.commit();

         System.out.println("User saved successfully");

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