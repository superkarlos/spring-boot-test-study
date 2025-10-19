package br.com.project.app.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import br.com.project.app.model.Anime;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@DataJpaTest
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    private Anime anime;
    private Anime animesave;

    @BeforeEach
    void setUp() {

        anime = new Anime();
        anime.setNome("carlos");
        animesave = creatObj(anime);
        assertThat(animesave).isNotNull();

    }

    @Test
    void bsucaPorNome() {
        Anime request = animeRepository.findByNome("carlos");
        assertThat(request).isNotNull();
        assertEquals(anime.getNome(), request.getNome());

    }

    @Test
    void dellAnime() {

        Integer idSavlo = animesave.getId();
        Optional<Anime> existente = animeRepository.findById(idSavlo);
        
        assertThat(existente).isPresent(); 
        animeRepository.delete(existente.get());
        Optional<Anime> deletado = animeRepository.findById(idSavlo);

    
        assertThat(deletado).isEmpty();
    }
    
    @Test
    void excptionAnimeHandler(){
        Anime anime = new Anime();
        assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(()->animeRepository.save(anime)).withMessageContaining("nome nulo");
    }

    private Anime creatObj(Anime anime) {
        return animeRepository.save(anime);
    }
   
}
