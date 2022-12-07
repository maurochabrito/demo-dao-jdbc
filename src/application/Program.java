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
		Department department = new Department(4,null);
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
		
		System.out.println("\n>>> Test #5: Seller Update");
		Seller uptadeSeller = sellerDao.findById(1);
		uptadeSeller.setName("Marta Wayne");
		sellerDao.uptade(uptadeSeller);
		System.out.println("\nUptade completed!");
		
		System.out.println("\n>>> Test #6: Delete Seller");
		Integer deleteId = 20;
		sellerDao.deleteById(deleteId);
		System.out.println("\nDone! Deleted Seller id = "+deleteId);
	}

}
