package br.com.project.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.project.app.dto.AnimeDto;
import br.com.project.app.model.Anime;
import br.com.project.app.repository.AnimeRepository;

@Service
public class AnimeService {

   // private List<Anime> animes = new ArrayList<>(List.of(new Anime(1, "Naturo"), new Anime(2, "Drgao baoun")));
   @Autowired
   private AnimeRepository animeRepository ;


    public List<Anime> getall() {
        return animeRepository.findAll();
    }

    public Anime getById(Integer id)throws Exception {
       Optional<Anime> reque = this.animeRepository.findById(id);

       if(reque.isEmpty()){
        throw new Exception("Anime not foud");
       }

       return reque.get();
    }

    public Anime save(AnimeDto animeDto){
        Anime newAnime = new Anime();
        newAnime.setNome(animeDto.nome());
        return this.animeRepository.save(newAnime);
    }

    public String dell(Integer id)throws Exception{
        animeRepository.deleteById(id);
        return "Removido";
    }

    public String put(AnimeDto data){
        if(data.id() == null){
            return "Id nulo";

        }
         if(data.nome() == null){
            return "nome nulo";
            
        }
        animeRepository.deleteById(data.id());
        save(data);

        return "Anime atualizado";
    }

    

}
