package com.api.exercico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.exercico.DTO.ProdutoDTO;
import com.api.exercico.domain.Produto;
import com.api.exercico.repositories.ProdutoRepository;
import com.api.exercico.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll(){
		List<Produto> list = repository.findAll();
		return list.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}

	
	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		
		Optional<Produto> obj = repository.findById(id);
		Produto entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity Not Fund"));
		return new ProdutoDTO(entity);
	}
	
	private Produto findByDescricao(ProdutoDTO dto) {
		
		Produto obj = repository.findByDescricao(dto.getDescricao());
		
		if(obj != null) {
			
		return obj;
		}
		return null;
	}


	public ProdutoDTO insert(ProdutoDTO dto) {
		
		if(findByDescricao(dto)!= null) {
			
			throw new ResourceNotFoundException("Descricao ja cadastrado");
		}
		Produto entity  = new Produto();
		entity.setAtivo(dto.isAtivo());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco_unitario(dto.getPreco_unitario());
		
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
	}


	public ProdutoDTO update(Long id , ProdutoDTO dto) {
		try {
			Produto entity = repository.getOne(id);
			entity.setDescricao(dto.getDescricao());
			entity.setAtivo(dto.isAtivo());
			entity.setPreco_unitario(dto.getPreco_unitario());
			repository.save(entity);
			return new ProdutoDTO(entity);
		} catch (EntityNotFoundException e) {
			
			 throw new ResourceNotFoundException("Entity Not Found");
		}

	}


	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException("Entity Not Found");
		}
		
	}
	
	
	
}
