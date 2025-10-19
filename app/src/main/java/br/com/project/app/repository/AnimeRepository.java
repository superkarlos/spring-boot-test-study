package br.com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.project.app.model.Anime;
import java.util.List;


@Repository
public interface AnimeRepository extends JpaRepository<Anime,Integer> {

    @Query("SELECT a FROM Anime a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Anime findByNome(@Param("nome") String nome);

}
