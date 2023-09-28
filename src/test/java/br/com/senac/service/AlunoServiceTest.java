package br.com.senac.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.senac.config.ModelConfig;
import br.com.senac.entity.Aluno;
import br.com.senac.repository.AlunoRepository;

@SpringBootTest
class AlunoServiceTest {
	
	private static final Integer ID = 1;
	
	private static final String Nome = "Lucas";
	
	private static final String Sobrenome = "Silva";
	
	private static final String Email = "lucas@gmail.com";
	
	private Aluno aluno;
	
	private Optional<Aluno> alunoOptional;
	
	@InjectMocks
	private AlunoService alunoService;
	@Mock
	private AlunoRepository alunoRepository;
	@Mock
	private ModelConfig Mapper;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startAluno();
		
	}
	
	
	
	private void startAluno() {
		aluno = new Aluno(ID,Nome,Sobrenome,Email);
		alunoOptional = Optional.of(new Aluno (ID,Nome,Sobrenome,Email));
	}
	
	@Test
	void whenFindByIdThenReturnAnAlunoInstance() {
		
		Mockito.when(alunoRepository.findById(Mockito.anyInt())).thenReturn(alunoOptional);
		Aluno response =alunoService.getAlunoByID(ID);
		Assertions.assertEquals(Aluno.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(Nome, response.getNome());
		Assertions.assertEquals(Sobrenome, response.getSobreNome());
		Assertions.assertEquals(Email, response.getEmail());
		
		
	}
	
}
