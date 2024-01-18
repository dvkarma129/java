package com.empmngt.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.empmngt.dto.DeptDto;
import com.empmngt.dto.EmpDto;
import com.empmngt.dto.ImageData;
import com.empmngt.exceptions.NotFoundExceptions;
import com.empmngt.repo.DeptRepo;
import com.empmngt.repo.EmpRepo;
import com.empmngt.repo.StorageRepository;
import com.empmngt.utils.ImageUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmpService {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	private final EmpRepo empRepo;
	private final DeptRepo deptRepo;
	private final StorageRepository repository;

	public List<EmpDto> getAllEmp() {
		return empRepo.findAll();
	}

	public EmpDto addEmp(EmpDto model) {
//	    DeptDto department = deptRepo.findById(model.getDepartment().getId()).orElseThrow(() -> new EntityNotFoundException("Department not found"));
		DeptDto department = deptRepo.findById(model.getDepartment().getId()).get();
		if (department == null) {
			throw new NotFoundExceptions("Department Not Found");
		}
		model.setDepartment(department);
		return empRepo.save(model);
	}

	public EmpDto getEmpById(Long id) {
		return empRepo.getById(id);
	}

	public EmpDto upadteEmp(EmpDto model) {
		return empRepo.save(model);
	}

	public void deleteEmp(long id) {
		empRepo.deleteById(id);
	}

	public List<EmpDto> addMulEmp(List<EmpDto> model) {
		return empRepo.saveAll(model);
	}

	@Transactional
	public List<EmpDto> getByCriteria(long salary) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpDto> criteriaQuery = criteriaBuilder.createQuery(EmpDto.class);
		Root<EmpDto> emps = criteriaQuery.from(EmpDto.class);
		criteriaQuery.select(emps);

		Predicate salaryPredicate = criteriaBuilder.greaterThan(emps.get("salary"), salary);
		criteriaQuery.where(salaryPredicate);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	public boolean existsByPhoneNumberAndEmail(String phoneNumber, String email) {
		return empRepo.existsByPhoneNumberAndEmail(phoneNumber, email);
	}

	public String uploadImage(MultipartFile file) throws IOException {

		ImageData imageData = repository.
				save(ImageData.builder().
						name(file.getOriginalFilename())
				.type(file.getContentType()).
				imageData(ImageUtils.compressImage(file.getBytes())).
				build());
		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] downloadImage(String fileName) {
		Optional<ImageData> dbImageData = repository.findByName(fileName);
		byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
		return images;
	}
}
