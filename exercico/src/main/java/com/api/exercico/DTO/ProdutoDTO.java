package com.api.exercico.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

import com.api.exercico.domain.Produto;

public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String descricao;
	private BigDecimal preco_unitario;
	private boolean ativo;
	
	
	public ProdutoDTO(Long id, String descricao, BigDecimal preco_unitario, boolean ativo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco_unitario = preco_unitario;
		this.ativo = ativo;
	}
	
	public ProdutoDTO(Produto produto) {
		
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.preco_unitario = produto.getPreco_unitario();
		this.ativo = produto.isAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(BigDecimal preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	

}
