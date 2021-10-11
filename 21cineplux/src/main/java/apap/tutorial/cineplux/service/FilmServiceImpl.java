package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.FilmModel;
import apap.tutorial.cineplux.repository.FilmDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FilmServiceImpl implements FilmService{

    @Autowired
    FilmDB filmDB;

    @Override
    public void addFilm(FilmModel film) {
        filmDB.save(film);
    }

    @Override
    public List<FilmModel> getListFilm() {
        return filmDB.findAll();
    }

    @Override
    public List<FilmModel> findByOrderByNamaFilmAsc() {
        return filmDB.findByOrderByNamaFilmAsc();
    }
}