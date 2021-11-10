package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.repository.BioskopDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class BioskopRestServiceImpl implements BioskopRestService{
    @Autowired
    private BioskopDB bioskopDB;

    @Override
    public BioskopModel createBioskop(BioskopModel bioskop) {
        return bioskopDB.save(bioskop);
    }

    @Override
    public List<BioskopModel> retrieveListBioskop(){
        return bioskopDB.findAll();
    }

    @Override
    public BioskopModel getBioskopByNoBioskop(Long noBioskop) {
        Optional<BioskopModel> bioskop = bioskopDB.findByNoBioskop(noBioskop);
        if (bioskop.isPresent()) {
            return bioskop.get();
        } else {
            throw new NoSuchElementException();
        }
    }
    @Override
    public BioskopModel updateBioskop(Long noBioskop, BioskopModel bioskopUpdate) {
        BioskopModel bioskop = getBioskopByNoBioskop(noBioskop);
        bioskop.setNamaBioskop(bioskopUpdate.getNamaBioskop());
        bioskop.setAlamatBioskop(bioskopUpdate.getAlamatBioskop());
        bioskop.setJumlahStudio(bioskopUpdate.getJumlahStudio());
        bioskop.setWaktuBuka(bioskopUpdate.getWaktuBuka());
        bioskop.setWaktuTutup(bioskopUpdate.getWaktuTutup());

        return bioskopDB.save(bioskop);
    }

    @Override
    public void deleteBioskop(Long noBioskop) {
        LocalTime now = LocalTime.now();
        BioskopModel bioskop = getBioskopByNoBioskop(noBioskop);

        if ((now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup()))
                && bioskop.getListPenjaga().isEmpty()) {
            bioskopDB.delete(bioskop);
        } else {
            throw new UnsupportedOperationException("Bioskop still open!")
        }
    }
}
