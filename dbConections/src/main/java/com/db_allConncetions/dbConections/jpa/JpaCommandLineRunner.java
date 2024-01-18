package com.db_allConncetions.dbConections.jpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.db_allConncetions.dbConections.dto.NewEmp;

@Component
public class JpaCommandLineRunner implements CommandLineRunner{

	@Autowired
	private SubRepo subRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 
		subRepo.insert(new Subjects(88,"Advik"));
	}
	
	

}
