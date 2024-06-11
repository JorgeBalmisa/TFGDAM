package com.example.main.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.main.model.Base;

@NoRepositoryBean  // Para que no se creen instancias de esta interfaz
public interface BaseRepository <T extends Base, ID extends Serializable> extends JpaRepository<T, ID>{

	
}
