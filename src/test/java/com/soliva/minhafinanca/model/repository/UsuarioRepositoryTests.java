package com.soliva.minhafinanca.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.soliva.minhafinanca.model.entity.Usuario;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTests {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		// Cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// Ação // Execução
		boolean result =repository.existsByEmail("usuario@gmail.com");
		
		// Verificação
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		 
		// Ação // Execução
		boolean result =repository.existsByEmail("usuario@gmail.com");
		
		// Verificação
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		// Cenario
		Usuario usuario = criarUsuario();
		
		// Ação
		Usuario usuarioSalvo = repository.save(usuario);
		
		// Verificação
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		
		// Cenario
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// Verificacao
		Optional<Usuario> result = repository.findByEmail("usuario@gmail.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQndoNaoExisteNaBase() {
		
		// Cenario
		
		// Verificacao
		Optional<Usuario> result = repository.findByEmail("usuario@gmail.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();
		
	}
	
	public static Usuario criarUsuario() {
		return Usuario
				.builder()
				.nome("usuario")
				.email("usuario@gmail.com")
				.senha("usuario")
				.build();
	}
 }
