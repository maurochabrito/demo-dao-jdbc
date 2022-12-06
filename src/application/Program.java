package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println(">>> Test #1: Find by Id");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		System.out.println("\n>>> Test #2: Find by Department");
		Department department = new Department(1,null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj: list) {
			System.out.println(obj);
		}
		System.out.println("\n>>> Test #3: Find All");
		List<Seller> list2 = sellerDao.findAll();
		for(Seller obj: list2) {
			System.out.println(obj);
		}
		System.out.println("\n>>> Test #4: Seller Insert");
		Seller newSeller = new Seller(null,"Greg","greg@gmail.com",new Date(),4000.0,department);
		sellerDao.insert(newSeller);
		System.out.println("\nInserted! New id = "+newSeller.getId());
	}

}
