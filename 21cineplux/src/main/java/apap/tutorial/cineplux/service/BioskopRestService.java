package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;

import java.util.List;

public interface BioskopRestService {
    BioskopModel createBioskop(BioskopModel bioskop);
    List<BioskopModel> retrieveListBioskop();
    BioskopModel getBioskopByNoBioskop(Long noBioskop);
    BioskopModel updateBioskop(Long noBioskop, BioskopModel bioskopUpdate);
    void deleteBioskop(Long noCabang);
}
