package com.mohit.doa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.mohit.model.LogProfile;

@Transactional
public class LogProfileDaoImpl {

	@PersistenceContext
	private EntityManager em;
	
	
	public Long save(LogProfile logpro) {
		em.persist(logpro);
		return logpro.getPid();
	}
	
	public List<LogProfile>getAll() {
		return em.createQuery("SELECT p FROM LogProfile p", LogProfile.class).getResultList();
	}
	
}
