package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		System.out.println(">>> Test #1: Find by Id");
		Department department = departmentDao.findById(3);
		System.out.println(department);
		
		System.out.println("\n>>> Test #2: Find All");
		List<Department> list = departmentDao.findAll();
		for(Department obj: list) {
			System.out.println(obj);
		}
		
		System.out.println("\n>>> Test #3: Department Insert");
		Department newDepartment = new Department(null,"Utilities");
		departmentDao.insert(newDepartment);
		System.out.println("\nInserted! New id = "+newDepartment.getId());
		
		System.out.println("\n>>> Test #4: Seller Update");
		Department uptadeDepartment = departmentDao.findById(4);
		uptadeDepartment.setName("Mangá");
		departmentDao.uptade(uptadeDepartment);
		System.out.println("\nUptade completed!");

		System.out.println("\n>>> Test #5: Delete ");
		Integer deleteId = 7;
		departmentDao.deleteById(deleteId);
		System.out.println("\nDone! Deleted Department id = "+deleteId);
	}
}
