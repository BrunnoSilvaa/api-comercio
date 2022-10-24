package com.api.exercico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.exercico.DTO.ProdutoDTO;
import com.api.exercico.domain.Produto;
import com.api.exercico.repositories.ProdutoRepository;
import com.api.exercico.services.exceptions.EntityNotFoundException;

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
		Produto entity = obj.orElseThrow(()-> new EntityNotFoundException("Entity Not Fund"));
		return new ProdutoDTO(entity);
	}
}
