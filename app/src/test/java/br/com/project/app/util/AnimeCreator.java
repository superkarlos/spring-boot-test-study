package br.com.project.app.util;

import br.com.project.app.model.Anime;

public class AnimeCreator {

    public static Anime createAnimeSemId(){
        Anime anime = new Anime();
        anime.setNome("Carlos");
        return anime;
    }

    
    
    public static Anime createAnimeComId(){
        Anime anime = new Anime();
        anime.setId(1);
        anime.setNome("Carlos");
        return anime;
    }

    public static Anime createAnimeComIdUpdate(){
        Anime anime = new Anime();
        anime.setId(1);
        anime.setNome("luiz");
        return anime;
    }
}
