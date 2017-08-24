package com.mapping.many2many;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author jang
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	private long id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "SALARY")
	private double salary;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable( name = "EMPLOYEE_ADDRESS", joinColumns = { @JoinColumn(name = "EMP_ID") }, inverseJoinColumns = { @JoinColumn(name = "ADDR_ID") })
	private Set<Address> addresses = new LinkedHashSet<>();

	public Employee() {
	}

	public Employee(String name, String designation, double salary) {
		this.name = name;
		this.designation = designation;
		this.salary = salary;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	// Setter and Getter methods

}