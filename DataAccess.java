package com.example.SpringAssignment3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataAccess {
	
	Connection connection=ConnectionClass.connection();
	public void create(Employee employee,Organization org) {
		if(checkPhoneNumber(employee))
		{
			System.out.println("phone number already exists");
			return;
		}
		if(checkOrganizationCode(employee.getOrganizationCode()))
		{
			//System.out.println("organization code is different");
			try {
			PreparedStatement stmt=connection.prepareStatement("Insert into emp(id,name,age,mobileNo) values(?, ?, ?, ?)");
			stmt.setInt(1, employee.getId());
			stmt.setString(2, employee.getName());
			stmt.setInt(3, employee.getAge());
			stmt.setString(4, employee.getMobileNo());
			stmt.execute();
			return;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		if(checkMaxEmp(employee,org))
		{
			System.out.println("max employee exceeded");
			return;
		}
		
			try {
				PreparedStatement stmt=connection.prepareStatement("Insert into emp(id,name,age,mobileNo,organizationCode) values(?, ?, ?, ?, ?)");
				stmt.setInt(1, employee.getId());
				stmt.setString(2, employee.getName());
				stmt.setInt(3, employee.getAge());
				stmt.setString(4, employee.getMobileNo());
				stmt.setString(5, employee.getOrganizationCode());
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	}
	private boolean checkMaxEmp(Employee employee, Organization org) {
		try {
			PreparedStatement stmt3=connection.prepareStatement("Select count(id) as cnt from emp where organizationCode=?");
			PreparedStatement stmt4=connection.prepareStatement("Select maxempallowed from organization where code='"+org.getCode()+"'");
			stmt3.setString(1,employee.getOrganizationCode());
//			stmt4.setString(1,org.getCode());
			ResultSet rs1=stmt3.executeQuery();
			ResultSet rs2=stmt4.executeQuery();
//			while(rs2.next())
//			{
//
//				System.out.println(rs2.getInt(1));
//			}
//			rs1.next();
//			rs2.next();
//			return rs1.getInt("cnt")>rs2.getInt("maxi");
			int cnt = 0;
			int maxi = 0;
			while (rs1.next() && rs2.next()) {
				cnt = rs1.getInt("cnt");
				maxi = rs2.getInt(1);
				System.out.println(maxi + "only maxi");
			}
//			while (rs2.next()) {
//				maxi = rs2.getInt(1);
//				System.out.println(maxi + "only maxi");
//			}
			System.out.println(cnt + " " + maxi);
			return cnt > maxi;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	private boolean checkOrganizationCode(String Org) {
//		try {
//			PreparedStatement stmt1=connection.prepareStatement("SELECT COUNT(*) FROM emp e JOIN Organization o ON e.organizationcode = o.code");
//   		stmt1.setString(1, employee.getOrganizationCode());
//			ResultSet rs=stmt1.executeQuery();
//			int cnt=0;
//			if(rs.next())
//			{
//				cnt=rs.getInt(1);
//			}
//			if(cnt>0)
//			{
//				System.out.println("organization code is same");
//				return false;
//			}
//			return true;
//		}
//		catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//			return true;
//		}
		try {
			PreparedStatement stmt4=connection.prepareStatement("select code from organization");
			ResultSet rs=stmt4.executeQuery();
			//rs.next();
			while(rs.next())
			{
				if(Org!=null && Org.equals(rs.getString("code")))
				{
					System.out.println("code is same");
					return false;
				}
			}
			System.out.println("code is different");
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	private boolean checkPhoneNumber(Employee employee) {
		try {
		PreparedStatement stmt2=connection.prepareStatement("SELECT COUNT(*) AS CNT FROM EMP WHERE mobileno=?");
		stmt2.setString(1, employee.getMobileNo());
		ResultSet rs=stmt2.executeQuery();
		if(rs.next())
		{
			int count=rs.getInt(1);
			return count>0;
		}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return false;
	}
	public void OrgCreate(Organization org) {
		try {
			PreparedStatement stmt=connection.prepareStatement("Insert into Organization(id,name,code,location,maxEmpAllowed) values(?, ?, ?, ?, ?)");
			stmt.setInt(1, org.getId());
			stmt.setString(2, org.getName());
			stmt.setString(3, org.getCode());
			stmt.setString(4, org.getLocation());
			stmt.setInt(5, org.getMaxEmpAllowed());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public void update(Employee employee,Organization org) {
		if(checkPhoneNumber(employee))
		{
			System.out.println("Mobile number is already in the database");
			return;
		}
		if(checkOrganizationCode(employee.getOrganizationCode()))
		{
			System.out.println("organization code is different");
			return;
		}
		if(checkMaxEmp(employee,org))
		{
			System.out.println("max employee exceeded");
			return;
		}
		try {
			PreparedStatement stmt=connection.prepareStatement("Update emp set name=?,age=?,mobileno=?,organizationcode=? where id=?");
			stmt.setString(1, employee.getName());
			stmt.setInt(2, employee.getAge());
			stmt.setString(3, employee.getMobileNo());
			stmt.setString(4,employee.getOrganizationCode());
			stmt.setInt(5, employee.getId());
			stmt.execute();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void Orgupdate(Organization org) {
		if(CheckCode(org))
		{
			System.out.println("Organization code is different");
			return;
		}
		try {
			PreparedStatement stmt=connection.prepareStatement("Update organization set name=?,code=?,location=?,maxempallowed=? where id=?");
			stmt.setString(1, org.getName());
			stmt.setString(2, org.getCode());
			stmt.setString(3, org.getLocation());
			stmt.setInt(4, org.getMaxEmpAllowed());
			stmt.setInt(5, org.getId());
			stmt.execute();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	private boolean CheckCode(Organization org) {
		try {
			PreparedStatement stmt=connection.prepareStatement("Select count(*) as cnt from organization where code=?");
			stmt.setString(1, org.getCode());
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return rs.getInt("cnt")<=0;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return true;
	}
	public List<Organization> view() {
		List<Organization> list=new ArrayList<>();
		try {
		PreparedStatement stmt=connection.prepareStatement("select * from organization");
		stmt.execute();
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
		{
			Organization org=new Organization();
			org.setId(rs.getInt(1));
			org.setName(rs.getString(2));
			org.setCode(rs.getString(3));
			org.setLocation(rs.getString(4));
			org.setMaxEmpAllowed(rs.getInt(5));
			list.add(org);
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return list;
	}
	public List<Employee> EmpView() {
		List<Employee> list=new ArrayList<>();
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from emp");
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				Employee employee=new Employee();
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setAge(rs.getInt(3));
				employee.setMobileNo(rs.getString(4));
				employee.setOrganizationCode(rs.getString(5));
				list.add(employee);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return list;
	}
	public Organization viewid(int id) {
		Organization org=new Organization();
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from organization where id=?");
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				org.setId(rs.getInt(1));
				org.setName(rs.getString(2));
				org.setCode(rs.getString(3));
				org.setLocation(rs.getString(4));
				org.setMaxEmpAllowed(rs.getInt(5));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return org;
	}
	public Employee OrgViewId(int id) {
		Employee emp=new Employee();
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from emp where id=?");
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setAge(rs.getInt(3));
				emp.setMobileNo(rs.getString(4));
				emp.setOrganizationCode(rs.getString(5));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return emp;
	}

}
