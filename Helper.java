package com.example.SpringAssignment3;

import java.util.List;

public class Helper {
	DataAccess dataAccess=new DataAccess();

	public String create(Employee employee, Organization org) {
		dataAccess.create(employee,org);
		return "employee added successfully";
	}

	public String OrgCreate(Organization org) {
		dataAccess.OrgCreate(org);
		return "organization added successfully";
	}

	public String update(Employee employee,Organization org) {
		dataAccess.update(employee,org);
		return "employee updated successfully";
	}

	public String OrgUpdate(Organization org) {
		dataAccess.Orgupdate(org);
		return "organization updated successfully";
	}

	public List<Organization> view() {
		return dataAccess.view();
	}

	public List<Employee> EmpView() {
		return dataAccess.EmpView();
	}

	public Organization viewid(int id) {
		return dataAccess.viewid(id);
	}

	public Employee OrgViewId(int id) {
		return dataAccess.OrgViewId(id);
	}
	
}
