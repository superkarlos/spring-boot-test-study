package br.com.project.app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.project.app.dto.AnimeDto;
import br.com.project.app.model.Anime;
import br.com.project.app.services.AnimeService;
import br.com.project.app.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Slf4j
@RequiredArgsConstructor // vai fazer injecao de dependecia em quem tem fila
@RequestMapping("/path")
public class AnimeController {

   private final DateUtil dateUtil;
   private final AnimeService animeService;

   @GetMapping("/")
   public ResponseEntity<Page<Anime>> getAll(Pageable pageable) {
      log.info(dateUtil.dataTimeLocalFormatStaly(LocalDateTime.now()));
      return ResponseEntity.ok(animeService.getall(pageable));
   }

   @GetMapping("/list")
   public ResponseEntity<List<Anime>> getAllList() {
      log.info(dateUtil.dataTimeLocalFormatStaly(LocalDateTime.now()));
      return ResponseEntity.ok(animeService.getallList());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Anime> getAnimeById(@PathVariable(value = "id") Integer id) throws Exception {
      return ResponseEntity.status(HttpStatus.OK).body(animeService.getById(id));
   }

   @PostMapping("/")
   public ResponseEntity<Anime> saveAnime(@Valid @RequestBody AnimeDto animeDto) {

      return ResponseEntity.status(HttpStatus.CREATED).body(animeService.save(animeDto));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> dellAnimeById(@PathVariable(value = "id") Integer id) throws Exception {
      return ResponseEntity.status(HttpStatus.OK).body(animeService.dell(id));
   }

   @PutMapping("/")
   public ResponseEntity<String> putMethodName(@RequestBody AnimeDto entity) {
      return ResponseEntity.status(HttpStatus.CREATED).body(animeService.put(entity));
   }

   @GetMapping("/teste")
   public ResponseEntity<?> getMethodName() {

      ResponseEntity<Anime> data = new RestTemplate().getForEntity("http://localhost:8080/path/3", Anime.class);

      Anime anime = new RestTemplate().getForObject("http://localhost:8080/path/3", Anime.class);
      return ResponseEntity.ok().body(anime);
   }

}
