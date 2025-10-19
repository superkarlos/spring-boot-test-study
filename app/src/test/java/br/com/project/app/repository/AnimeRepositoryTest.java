package br.com.project.app.repository;


import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import br.com.project.app.model.Anime;
import lombok.extern.log4j.Log4j2;

@Log4j2
@DataJpaTest
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @BeforeEach
    void setUp(){

        Anime anime = new Anime();
        anime.setNome("carlos");
        Anime resp = creatObj(anime);
        assertThat(resp).isNotNull();

    }

    @Test
    void bsucaPorNome(){
        Anime nome = animeRepository.findByNome("carlos");
        assertEquals("carlos", nome.getNome());

    }

    private Anime creatObj (Anime anime){
        return animeRepository.save(anime);
    }
}
