package br.com.senac.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.dto.ProfessorDTO;
import br.com.senac.entity.Professor;
import br.com.senac.service.ProfessorService;

@RestController
@RequestMapping("professor")
public class ProfessorResource {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	public ResponseEntity<ProfessorDTO> cadastrarProf(@RequestBody ProfessorDTO profDTO) {
		
		Professor prof = mapper.map(profDTO, Professor.class);
		
		prof = professorService.salvar(prof);
		
		ProfessorDTO profNovo = mapper.map(prof, ProfessorDTO.class);
		
		return ResponseEntity.ok().body(profNovo);
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> buscarTodosProf() {
		List<Professor> listaProf = professorService.buscarTodosProf();
		
		List<ProfessorDTO> listaProfDTO = listaProf.stream().map(prof -> 
				mapper.map(prof, ProfessorDTO.class)).collect(Collectors.toList());
				
		
		return ResponseEntity.ok().body(listaProfDTO);
		
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfessorDTO> buscarProfPorID(@PathVariable("id") Integer id){
		Professor prof = professorService.getProfByID(id);
		ProfessorDTO professorDTO = mapper.map(prof, ProfessorDTO.class);
		return ResponseEntity.ok().body(professorDTO);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ProfessorDTO> atualizarProf(@PathVariable("id") Integer id,@RequestBody ProfessorDTO profDTO){
		
		Professor prof = mapper.map(profDTO, Professor.class);
		
		prof = professorService.updateProf(id, prof);
		
		ProfessorDTO profAlteradoDTO = mapper.map(prof, ProfessorDTO.class);
		
		return ResponseEntity.ok().body(profAlteradoDTO);
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirProf(@PathVariable("id") Integer id){
		Boolean flag = professorService.deleteProf(id);
		return ResponseEntity.ok().body(flag);
	}
	
	
	
	
}
