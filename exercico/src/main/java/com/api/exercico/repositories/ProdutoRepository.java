package com.api.exercico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.exercico.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT obj FROM Produto obj WHERE obj.descricao =:descricao")
	Produto findByDescricao(@Param("descricao") String descricao);
	


}
