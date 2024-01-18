package com.db_allConncetions.dbConections.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Repository
@Transactional
public class SubRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Subjects sub) {
		entityManager.merge(sub);
	}
}
