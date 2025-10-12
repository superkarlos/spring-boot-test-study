package br.com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.app.model.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime,Integer> {
    
}
