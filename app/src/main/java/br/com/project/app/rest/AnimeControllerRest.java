package br.com.project.app.rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.project.app.dto.AnimeDto;
import br.com.project.app.err.BadRequesteexcption;
import br.com.project.app.model.Anime;
import br.com.project.app.services.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor // vai fazer injecao de dependecia em quem tem fila
@RequestMapping("/rest")
public class AnimeControllerRest {

    private final AnimeService animeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Integer id) {

        try {
            Anime anime = new RestTemplate().getForObject("http://localhost:8080/path/" + id, Anime.class);
            Anime anime2 = animeService.getById(anime.getId());
            return ResponseEntity.ok().body((anime2));

        } catch (Exception e) {

            e.printStackTrace();
        }
        return ResponseEntity.ok().body(("Anime não cadastrado!"));
    }
    @GetMapping("/todos")
    public ResponseEntity<?> getMethodNameAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            // faz a requisição GET e obtém uma lista de Animes
            ResponseEntity<?> response = restTemplate.getForEntity(
                "http://localhost:8080/path/list", List.class
            );
    
            return ResponseEntity.ok(response.getBody());
    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao buscar lista de animes!");
        }
    }
    

    @PostMapping("/PostRest")
    public ResponseEntity<String> restPost(@Valid @RequestBody AnimeDto animeDto) {

        Anime newAnime = new Anime();
        BeanUtils.copyProperties(animeDto, newAnime);
        Object resq = new RestTemplate().postForEntity("http://localhost:8080/path/", newAnime, Anime.class);

        if (resq == null) {
            throw new BadRequesteexcption("Anime não cadastrado!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Anime cadastrado corretamente");

    }

}
