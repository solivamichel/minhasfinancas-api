package com.soliva.minhafinanca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soliva.minhafinanca.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	
}
