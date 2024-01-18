
package hospitalMangementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hospitalMangementSystem.Entity.Department;
import hospitalMangementSystem.Entity.Patient;

public interface DepartmentRepo extends JpaRepository<Department, Long>{

	boolean existsByName(String name);

}
