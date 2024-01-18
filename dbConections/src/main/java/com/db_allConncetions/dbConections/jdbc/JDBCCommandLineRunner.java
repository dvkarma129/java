package com.db_allConncetions.dbConections.jdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.db_allConncetions.dbConections.dto.NewEmp;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner{

	@Autowired
	private JdbcRepo jdbcRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 
//		jdbcRepo.insert(new NewEmp(57,"Advik","sdvgd"));
//		jdbcRepo.insert(new NewEmp(55,"Advik","sdvgd"));
//		jdbcRepo.insert(new NewEmp(56,"Advik","sdvgd"));
		
		jdbcRepo.delete(57);
	}
	
	

}
