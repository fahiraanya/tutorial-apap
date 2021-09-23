//package apap.tutorial.cineplux.service;
//
//import apap.tutorial.cineplux.model.BioskopModel;
//import ch.qos.logback.core.util.COWArrayList;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class BioskopInMemoryService implements BioskopService {
//    private List<BioskopModel> listBioskop;
//
//    public BioskopInMemoryService() {
//        listBioskop = new ArrayList<>();
//    }
//
//    @Override
//    public void addBioskop(BioskopModel bioskop) {
//        listBioskop.add(bioskop);
//    }
//
//    @Override
//    public List<BioskopModel> getBioskopList() {
//        return listBioskop;
//    }
//
//    @Override
//    public BioskopModel getBioskopByIdBioskop(String idBioskop) {
//        for (BioskopModel a: listBioskop) {
//            if (a.getIdBioskop().equals(idBioskop)) {
//                return a;
//            }
//        }
//        return null;
//    }
//    @Override
//    public void removeBioskop(BioskopModel bioskop) {
//        listBioskop.remove(bioskop);
//    }
//}
