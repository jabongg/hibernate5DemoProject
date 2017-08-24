package com.nt.cfgs;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static Map<String, SessionFactory> SESSION_FACTORY_MAP = new HashMap<String, SessionFactory>();

	private static StandardServiceRegistry registry;
	   private static SessionFactory sessionFactory;
	   public static SessionFactory getSessionFactory(String dbName) {
			if (SESSION_FACTORY_MAP.get(dbName) != null) {
				return SESSION_FACTORY_MAP.get(dbName);
			}

		   
	      if (sessionFactory == null) {
	         try {

	            // Create registry builder
	            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
	            registryBuilder.configure("hibernate-emp.cfg.xml");

	            // Hibernate settings equivalent to hibernate.cfg.xml's properties
	            Map<String, String> settings = new HashMap<>();
	           // settings.put(Environment.DRIVER, "org.postgresql.Driver");
	            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/SPADE_DB");
	            settings.put(Environment.USER, "postgres");
	            settings.put(Environment.PASS, "postgres");
	           // settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");

	            // Apply settings
	            registryBuilder.applySettings(settings);

	            // Create registry
	            registry = registryBuilder.build();

	            // Create MetadataSources
	            MetadataSources sources = new MetadataSources(registry);

	            // Create Metadata
	            Metadata metadata = sources.getMetadataBuilder().build();

	            // Create SessionFactory
	            sessionFactory = metadata.getSessionFactoryBuilder().build();

	         } catch (Exception e) {
	            e.printStackTrace();
	            if (registry != null) {
	               StandardServiceRegistryBuilder.destroy(registry);
	            }
	         }
	         
			// Put in Map
			SESSION_FACTORY_MAP.put(dbName, sessionFactory);
			return sessionFactory;

	      }
	      return sessionFactory;
	   }

	   public static void shutdown() {
	      if (registry != null) {
	         StandardServiceRegistryBuilder.destroy(registry);
	      }
	   }
	   
}
