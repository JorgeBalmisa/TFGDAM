package com.example.main.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.main.entities.Base;

public interface BaseController<T extends Base, ID extends Serializable> {
	public ResponseEntity<?> getAll();

	public ResponseEntity<?> getOne(@PathVariable ID id);

	public ResponseEntity<?> save(@RequestBody T entity);

	public ResponseEntity<?> update(@PathVariable ID id, @RequestBody T entity);

	public ResponseEntity<?> delete(@PathVariable ID id);

}
