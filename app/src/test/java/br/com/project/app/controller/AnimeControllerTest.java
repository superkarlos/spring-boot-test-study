package br.com.project.app.controller;

import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.PageImpl;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.app.model.Anime;
import br.com.project.app.services.AnimeService;
import br.com.project.app.util.AnimeCreator;
import br.com.project.app.util.DateUtil;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService service;

    @Spy
    private DateUtil dateUtil;

    @BeforeEach
    void setUp() {
        PageImpl<Anime> animes = new PageImpl<>(List.of(AnimeCreator.createAnimeComId()));
        BDDMockito.when(service.getall(ArgumentMatchers.any())).thenReturn(animes);
        doReturn("ignored").when(dateUtil).dataTimeLocalFormatStaly(any());

        BDDMockito.when(service.getallList()).thenReturn(List.of(AnimeCreator.createAnimeComId()));

    }

    @Test
    void listallPege() {
        Anime exptc = AnimeCreator.createAnimeComId();

        PageImpl<Anime> animes = (PageImpl<Anime>) animeController.getAll(any()).getBody();

        assertThat(animes).isNotNull();
        verify(service, times(1)).getall(any());
        assertEquals(exptc.getNome(), animes.toList().get(0).getNome());
    }

    @Test
    void listallNotPege() {
       Anime exptc = AnimeCreator.createAnimeComId();

       List<Anime> animes = (List<Anime>) animeController.getAllList().getBody();

        assertThat(animes).isNotNull();
        verify(service, times(1)).getallList();
        assertEquals(exptc.getNome(), animes.get(0).getNome());
    }

}
