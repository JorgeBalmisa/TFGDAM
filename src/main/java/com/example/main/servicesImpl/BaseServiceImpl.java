package com.example.main.servicesImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.main.entities.Base;
import com.example.main.entities.Cliente;
import com.example.main.repositories.BaseRepository;
import com.example.main.services.BaseService;

import jakarta.transaction.Transactional;

public abstract class BaseServiceImpl<T extends Base, ID extends Serializable> implements BaseService<T, ID> {
	protected BaseRepository<T, ID> baseRepository;

	public BaseServiceImpl(BaseRepository<T, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	@Transactional
	public List<T> findAll() throws Exception {
		try {
			List<T> entities = baseRepository.findAll();
			return entities;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public T findById(ID id) throws Exception {
		try {
			Optional<T> entityOptional = baseRepository.findById(id);
			return entityOptional.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public T save(T entity) throws Exception {
		try {
			entity = baseRepository.save(entity);
			return entity;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public T update(ID id, T entity) throws Exception {
		try {
			Optional<T> entityOptional = baseRepository.findById(id);
			T entityUpdate = entityOptional.get();
			entityUpdate = baseRepository.save(entity);
			return entityUpdate;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public boolean delete(ID id) throws Exception {
		try {
			if (baseRepository.existsById(id)) {
				baseRepository.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
