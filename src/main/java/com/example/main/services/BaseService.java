package com.example.main.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.main.entities.Base;

public interface BaseService<T extends Base, ID extends Serializable> {
	public List<T> findAll() throws Exception;
	
	public Page<T> findAll(Pageable pageable) throws Exception;
	
	public T findById(ID id) throws Exception;
	
	public T save(T entity) throws Exception;
	
	public T update(ID id, T entity) throws Exception;
	
	public boolean delete(ID id) throws Exception;
}
