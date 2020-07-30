package com.employee.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {

	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int department_ID;
	private String short_Name;
	private String department_Name;
	
	public Department() {
	
	}
	
	public Department(int departmentID){
		super();
		this.department_ID = departmentID;
	}
	
	public Department(int departmentID, String short_Name, String department_Name) {
		super();
		this.department_ID = departmentID;
		this.short_Name = short_Name;
		this.department_Name = department_Name;
	}

	public int getDepartment_ID() {
		return department_ID;
	}

	public void setDepartment_ID(int department_ID) {
		this.department_ID = department_ID;
	}

	public String getShort_Name() {
		return short_Name;
	}

	public void setShort_Name(String short_Name) {
		this.short_Name = short_Name;
	}

	public String getDepartment_Name() {
		return department_Name;
	}

	public void setDepartment_Name(String department_Name) {
		this.department_Name = department_Name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + department_ID;
		result = prime * result + ((department_Name == null) ? 0 : department_Name.hashCode());
		result = prime * result + ((short_Name == null) ? 0 : short_Name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (department_ID != other.department_ID)
			return false;
		if (department_Name == null) {
			if (other.department_Name != null)
				return false;
		} else if (!department_Name.equals(other.department_Name))
			return false;
		if (short_Name == null) {
			if (other.short_Name != null)
				return false;
		} else if (!short_Name.equals(other.short_Name))
			return false;
		return true;
	}

	
}
