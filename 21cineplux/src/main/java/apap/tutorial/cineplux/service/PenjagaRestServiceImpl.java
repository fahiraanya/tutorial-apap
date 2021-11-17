package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.PenjagaDB;
import apap.tutorial.cineplux.rest.UmurPenjaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PenjagaRestServiceImpl implements PenjagaRestService{
    private final WebClient webClient;

    @Autowired
    private PenjagaDB penjagaDB;

    public PenjagaRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.agify.io").build();
    }

    @Override
    public PenjagaModel createPenjaga(PenjagaModel penjaga) {
        return penjagaDB.save(penjaga);
    }

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga) {
        Optional<PenjagaModel> p = Optional.ofNullable(penjagaDB.findByNoPenjaga(noPenjaga));
        if(p.isPresent()) {
            return p.get();
        } else {
            throw new NoSuchElementException();
        }
    }
    @Override
    public PenjagaModel updatePenjaga(Long noPenjaga, PenjagaModel penjagaUpdate) {
        PenjagaModel p = getPenjagaByNoPenjaga(noPenjaga);
        p.setNamaPenjaga(penjagaUpdate.getNamaPenjaga());
        p.setJenisKelamin(penjagaUpdate.getJenisKelamin());

        return penjagaDB.save(p);
    }
    @Override
    public List<PenjagaModel> retrieveListPenjaga() {
        return penjagaDB.findAll();
    }

    @Override
    public void deletePenjaga(Long noPenjaga) {
        LocalTime now = LocalTime.now();
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);

        if((now.isBefore(penjaga.getBioskop().getWaktuBuka()) || now.isAfter(penjaga.getBioskop().getWaktuTutup()))) {
            penjagaDB.delete(penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop still open!");
        }
    }
    @Override
    public Mono<UmurPenjaga> umurPenjaga(Long noPenjaga) {
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        return this.webClient.get().uri("?name=" + penjaga.getNamaPenjaga())
                .retrieve()
                .bodyToMono(UmurPenjaga.class);
    }
}
