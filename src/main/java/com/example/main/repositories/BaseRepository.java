package com.example.main.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.example.main.entities.Base;
import com.example.main.entities.Persona;

@NoRepositoryBean  // Para que no se creen instancias de esta interfaz
public interface BaseRepository <T extends Base, ID extends Serializable> extends JpaRepository<T, ID>{

	
}
