package com.api.exercico.services;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.exercico.DTO.ProdutoDTO;
import com.api.exercico.domain.Produto;
import com.api.exercico.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll(){
		
		List<Produto> list = repository.findAll();
		return list.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}
}
