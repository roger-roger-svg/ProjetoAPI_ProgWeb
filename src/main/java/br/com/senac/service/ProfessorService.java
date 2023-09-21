package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Professor;
import br.com.senac.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository repo;
	
	public Professor salvar(Professor prof) {
		 return repo.save(prof);
		
	}
	
	public List<Professor> buscarTodosProf(){
		return repo.findAll();
	}
	
	public Professor getProfByID(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public Boolean deleteProf(Integer id) {
		Professor prof = repo.findById(id).orElse(null);
		if(prof != null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Professor updateProf(Integer id, Professor profAlterado) {
		Professor profBD = repo.findById(id).orElse(null);
		if(profBD != null) {
			profBD.setEmail(profAlterado.getEmail());
			profBD.setNome(profAlterado.getNome());
			profBD.setSobreNome(profAlterado.getSobreNome());
			return repo.save(profBD);
		}else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
