package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.BioskopDB;
import apap.tutorial.cineplux.repository.PenjagaDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
@Transactional
public class PenjagaServiceImpl implements PenjagaService{
    @Autowired
    PenjagaDB penjagaDB;

    @Autowired
    BioskopDB bioskopDB;

    @Override
    public void addPenjaga(PenjagaModel penjaga) { penjagaDB.save(penjaga); }

    @Override
    public boolean updatePenjaga(PenjagaModel penjaga) {
        PenjagaModel penjagaUpdate = penjagaDB.findByNoPenjaga(penjaga.getNoPenjaga());

        BioskopModel bioskop = bioskopDB.findByNoBioskop(penjagaUpdate.getBioskop().getNoBioskop()).get();
        LocalTime now = LocalTime.now();
        LocalTime openTime = bioskop.getWaktuBuka();
        LocalTime closedTime = bioskop.getWaktuTutup();

        if(now.isBefore(openTime) || now.isAfter(closedTime)) {
            penjagaUpdate.setNamaPenjaga(penjaga.getNamaPenjaga());
            penjagaUpdate.setJenisKelamin(penjaga.getJenisKelamin());
            penjagaDB.save(penjagaUpdate);
            return true;
        } else {
            return false;
        }

    }
    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga) {
        PenjagaModel penjaga = penjagaDB.findByNoPenjaga(noPenjaga);
        return penjaga;
    }
    @Override
    public int deletePenjaga(PenjagaModel penjaga) {
        LocalTime now = LocalTime.now();
        BioskopModel bioskop = bioskopDB.findByNoBioskop(penjaga.getBioskop().getNoBioskop()).get();
        if (now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup())) {
            penjagaDB.delete(penjaga);
            return 1;
        }
        return 0;
    }
}
