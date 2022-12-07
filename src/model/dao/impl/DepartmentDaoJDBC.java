package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.PreparableStatement;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao {

	Connection conn;
	
	public DepartmentDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO department\r\n"
					+ "(Name)\r\n"
					+ "VALUES\r\n"
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, department.getName());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected error: No rows affected!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void uptade(Department department) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE department\r\n"
					+ "SET Name = ?\r\n"
					+ "WHERE Id = ?",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, department.getName());
			st.setInt(2, department.getId());
			st.executeUpdate();

		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM department\r\n"
					+ "WHERE Id = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			if (rows == 0) {
				throw new DbException("Required delete department doesn't exists! (id = "+id+" )");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department department = instantiateDepartment(rs);//new Department();
				//department.setId(rs.getInt("Id"));
				//department.setName(rs.getString("Name"));
				return department;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"SELECT *FROM department ORDER BY Name");
			rs = st.executeQuery();
			List<Department> list = new ArrayList<>();
			while(rs.next()) {
				Department department = instantiateDepartment(rs);
				list.add(department);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}
}
