package criteriaprac.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import criteriaprac.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Long>,JpaSpecificationExecutor<Student>{

}
