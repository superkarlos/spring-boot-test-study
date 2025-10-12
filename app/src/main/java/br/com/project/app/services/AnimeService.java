package br.com.project.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.project.app.dto.AnimeDto;
import br.com.project.app.model.Anime;

@Service
public class AnimeService {

    private List<Anime> animes = new ArrayList<>(List.of(new Anime(1, "Naturo"), new Anime(2, "Drgao baoun")));

    public List<Anime> getall() {
        return animes;
    }

    public Anime getById(Integer id) {
        return animes.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found!"));
    }

    public Anime save(AnimeDto animeDto){
        Anime newAnime = new Anime(animeDto.id(),animeDto.nome());
        animes.add(newAnime);
        return newAnime;
    }

    public String dell(Integer id){
        animes.remove(getById(id));
        return "Removido";
    }

    public String put(AnimeDto data){
        if(data.id() == null){
            return "Id nulo";

        }
         if(data.nome() == null){
            return "nome nulo";
            
        }
        dell(data.id());
        save(data);

        return "Anime atualizado";
    }

    

}
