package com.soliva.minhafinanca.api.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class LancamentoDto {
	
	private Long id;
	
	private String descricao;
	
	private Integer mes;
	
	private Integer ano;
	
	private BigDecimal valor;
	
	private Long Usuario;
	
	private String tipo;
	
	private String status;
}
