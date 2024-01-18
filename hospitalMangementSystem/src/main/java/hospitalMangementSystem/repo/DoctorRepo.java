
package hospitalMangementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hospitalMangementSystem.Entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long>{

	boolean existsByContact(String contact);

}
