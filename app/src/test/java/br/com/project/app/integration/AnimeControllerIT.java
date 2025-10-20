package br.com.project.app.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.project.app.dto.AnimeDto;
import br.com.project.app.model.Anime;
import br.com.project.app.repository.AnimeRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class AnimeControllerIT {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AnimeRepository animeRepository;

    @LocalServerPort
    private int port;

    @Test
    void init() {
        Anime anime = new Anime();
        anime.setNome("carlos");
        animeRepository.save(anime);

        String url = "http://localhost:" + port + "/path/list";

        List<Anime> listAll = restTemplate.exchange( url,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Anime>>() {
                }).getBody();

        assertEquals(listAll.get(0).getNome(), "carlos");
    }

    @Test
    void getbyId() {
        Anime anime = new Anime();
        anime.setNome("carlos");
        Anime res = animeRepository.save(anime);

        String url = "http://localhost:" + port + "/path/" + res.getId();

        Anime request = restTemplate.getForObject(url, Anime.class);

        assertThat(request).isNotNull();
        assertEquals(res.getId(), request.getId());
        assertEquals(res.getId(), animeRepository.findById(res.getId()).get().getId());

    }

    @Test
    void getbyNome() {
        Anime anime = new Anime();
        anime.setNome("carlos");
        animeRepository.save(anime);

        String url = "http://localhost:" + port + "/path/list";

        List<Anime> listAll = restTemplate.exchange( url,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Anime>>() {
                }).getBody();

        assertEquals(listAll.get(0).getNome(), "carlos");
    }

    @Test
void saveAnime_shouldCreateNewAnime() {

    String url = "http://localhost:" + port + "/path/";

    AnimeDto dto = new AnimeDto(1,"Naruto");
    
    ResponseEntity<Anime> response = restTemplate.postForEntity(url, dto, Anime.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    Anime saved = response.getBody();
    assertThat(saved).isNotNull();
    assertThat(saved.getId()).isNotNull();
    assertThat(saved.getNome()).isEqualTo("Naruto");

    Optional<Anime> repoAnime = animeRepository.findById(saved.getId());
    assertThat(repoAnime).isPresent();
    assertThat(repoAnime.get().getNome()).isEqualTo("Naruto");
}

}
