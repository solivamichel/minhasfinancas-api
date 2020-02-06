package com.soliva.minhafinanca.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soliva.minhafinanca.api.dto.LancamentoDto;
import com.soliva.minhafinanca.exception.RegraNegocioException;
import com.soliva.minhafinanca.model.entity.Lancamento;
import com.soliva.minhafinanca.model.entity.Usuario;
import com.soliva.minhafinanca.model.enums.StatusLancamento;
import com.soliva.minhafinanca.model.enums.TipoLancamento;
import com.soliva.minhafinanca.service.LancamentoService;
import com.soliva.minhafinanca.service.UsuarioService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {
	
	private LancamentoService service;
	
	private UsuarioService usuarioService; 
	
	public LancamentoResource(LancamentoService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity salvar( @RequestBody LancamentoDto dto ) {
		
	}
	
	private Lancamento converter(LancamentoDto dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		Usuario usuario = usuarioService.obterPorId(dto.getUsuario()).orElseThrow( () -> new RegraNegocioException("Usuario não encontrado para o Id Informado."));
		
		lancamento.setUsuario(usuario);
		lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		return lancamento;
	}
}
