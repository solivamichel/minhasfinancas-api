package com.soliva.minhafinanca.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.soliva.minhafinanca.exception.RegraNegocioException;
import com.soliva.minhafinanca.model.entity.Usuario;
import com.soliva.minhafinanca.model.repository.UsuarioRepository;
import com.soliva.minhafinanca.service.impl.UsuarioServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	UsuarioService service;
	
	UsuarioRepository repository;
	
	@Before
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test(expected = Test.None.class)
	public void deveValidarEmail() {
		// Cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		// Acao
		service.validarEmail("email@email.com");
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		// Cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
		repository.save(usuario);
		
		// Ação
		service.validarEmail("email@email.com");
	}
}
