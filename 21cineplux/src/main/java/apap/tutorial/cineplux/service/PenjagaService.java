package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.PenjagaModel;

public interface PenjagaService {
    void addPenjaga(PenjagaModel penjaga);
    boolean updatePenjaga(PenjagaModel penjaga);
    PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga);
    int deletePenjaga(PenjagaModel penjaga);
}
