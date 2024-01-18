
package hospitalMangementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hospitalMangementSystem.Entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long>{

	boolean existsByNameAndContact(String name, String contact);

	Patient findByNameAndContact(String name, String contact);

}
