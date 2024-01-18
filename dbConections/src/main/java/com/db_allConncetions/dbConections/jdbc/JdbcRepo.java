package com.db_allConncetions.dbConections.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.db_allConncetions.dbConections.dto.NewEmp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Repository
@AllArgsConstructor(onConstructor_ = @Autowired)
@NoArgsConstructor
public class JdbcRepo {

	private JdbcTemplate springJdbcTemplate;
	
	
	private static String Insert_Query =
		"""
		INSERT INTO `new_emp` (id, email, name) VALUES (?,?,?);
		""";
	
	private static String delete_Query =
			"""
			DELETE FROM `new_emp` WHERE `new_emp`.`id` = ?"
			""";
	
	public void insert(NewEmp newEmp) {
		springJdbcTemplate.update(Insert_Query,newEmp.getId(),newEmp.getEmail(),newEmp.getName());
	}
	
	public void delete(int id) {
		springJdbcTemplate.update(delete_Query,id);
	}
}
