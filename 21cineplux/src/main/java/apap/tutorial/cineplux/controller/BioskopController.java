package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.PenjagaService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

@Controller
public class BioskopController {
    @Qualifier("bioskopServiceImpl")
    @Autowired
    private BioskopService bioskopService;

    @GetMapping("/bioskop/add")
    public String addBioskopForm(Model model) {
        model.addAttribute("bioskop", new BioskopModel());
        return "form-add-bioskop";
    }

    @PostMapping("/bioskop/add")
    public String addBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        bioskopService.addBioskop(bioskop);
        model.addAttribute("noBioskop", bioskop.getNoBioskop());
        return "add-bioskop";
    }

    @GetMapping("/bioskop/viewall")
    public String listBioskop(Model model) {
        List<BioskopModel> listBioskop = bioskopService.findByOrderByNamaBioskopAsc();
        model.addAttribute("listBioskop", listBioskop);
        return "viewall-bioskop";
    }

    @GetMapping("/bioskop/view")
    public String viewDetailBioskop(
            @RequestParam(value = "noBioskop") Long noBioskop,
            Model model
    ) {
    BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
    List<PenjagaModel> listPenjaga = bioskop.getListPenjaga();

    model.addAttribute("bioskop", bioskop);
    model.addAttribute("listPenjaga", listPenjaga);
    return "view-bioskop";
    }

    @GetMapping("/bioskop/update/{noBioskop}")
    public String updateBioskopForm(
            @PathVariable Long noBioskop,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        model.addAttribute("bioskop", bioskop);
        return "form-update-bioskop";
    }

    @PostMapping("/bioskop/update")
    public String updateBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();
        for (BioskopModel a : listBioskop) {
            if (a.getNamaBioskop().equalsIgnoreCase(bioskop.getNamaBioskop())) {
                return "update-bioskop-fail";
            }
        }
        bioskopService.updateBioskop(bioskop);
        model.addAttribute("noBioskop",bioskop.getNoBioskop());
        return "update-bioskop";
    }

    @GetMapping("/bioskop/delete/{noBioskop}")
    public String removeBioskopByNoBioskop(
            @PathVariable Long noBioskop,
            @ModelAttribute BioskopModel bioskopModel,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        if (bioskop == null) {
            return "no-bioskop";
        }
        LocalTime now = LocalTime.now();
        LocalTime openTime = bioskop.getWaktuBuka();
        LocalTime closedTime = bioskop.getWaktuTutup();

        List<PenjagaModel> listPenjaga = bioskop.getListPenjaga();
        if (listPenjaga.size() == 0 && (now.isBefore(openTime) || now.isAfter(closedTime))) {
            bioskopService.deleteBioskop(bioskop);
            return "delete-bioskop-sukses";
        }
        else {
            return "delete-bioskop-fail";
        }
    }

}

//    Routing URL yang diinginkan
//    @RequestMapping("/bioskop/add")
//    public String addBioskop(
//            @RequestParam(value = "idBioskop", required = true) String idBioskop,
//            @RequestParam(value = "namaBioskop", required = true) String namaBioskop,
//            @RequestParam(value = "alamat", required = true) String alamat,
//            @RequestParam(value = "noTelepon", required = true) String noTelepon,
//            @RequestParam(value = "jumlahStudio", required = true) int jumlahStudio,
//            Model model
//    ) {
//        // Membuat objek BioskopModel
//        BioskopModel bioskopModel = new BioskopModel(idBioskop, namaBioskop,alamat,noTelepon,jumlahStudio);
//
//        // Menambahkan objek BioskopModel ke dalam service
//        bioskopService.addBioskop(bioskopModel);
//
//        // Add variabel id bioskop ke "idBioskop" untuk dirender ke dalam thymeleaf
//        model.addAttribute("idBioskop", idBioskop);
//
//        // Return view template yang digunakan
//        return "add-bioskop";
//    }
//    @RequestMapping("/bioskop/viewall")
//    public String listBioskop(Model model){
//        // Mendapatkan semua bioskop
//        List<BioskopModel> listBioskop = bioskopService.getBioskopList();
//
//        // Add variabel semua BioskopModel ke 'listBioskop' untuk dirender dalam thymeleaf
//        model.addAttribute("listBioskop", listBioskop);
//
//        //Return view template yang diinginkan
//        return "viewall-bioskop";
//    }
//    @RequestMapping("/bioskop/view")
//    public String detailBioskop(
//            @RequestParam(value = "idBioskop", required = true) String idBioskop,
//            Model model
//    ) {
//        // Mendapatkan bioskop sesuai dengan idBioskop
//        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
//
//        // Add variabel BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
//        model.addAttribute("bioskop", bioskopModel);
//
//        return "view-bioskop";
//    }
//    @RequestMapping("bioskop/view/id-bioskop/{idBioskop}")
//    public String viewBioskopWithPathVariable(
//            @PathVariable(value = "idBioskop") String idBioskop,
//            Model model
//    ){
//        // Mendapatkan bioskop sesuai dengan idBioskop
//        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
//
//        // Cek bioskop
//        if (bioskopModel == null) {
//            return "no-bioskop";
//        }
//        // Add variable BioskopModel ke 'bioskop' untuk dirende dalam thymeleaf
//        model.addAttribute("bioskop", bioskopModel);
//        return "view-bioskop";
//    }
//    @RequestMapping("bioskop/update/id-bioskop/{idBioskop}/jumlah-studio/{jumlahStudio}")
//    public String uodateJumlahStudioWithPathVariable(
//            @PathVariable(value = "idBioskop") String idBioskop,
//            @PathVariable(value = "jumlahStudio") int jumlahStudio,
//            Model model
//    ){
//        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
//
//        if (bioskopModel == null) {
//            return "no-bioskop";
//        }
//        // Set jumlah studio baru
//        bioskopModel.setJumlahStudio(jumlahStudio);
//
//        // Add variabel BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
//        model.addAttribute("bioskop", bioskopModel);
//
//        return "update-bioskop";
//    }
//
//    @RequestMapping("bioskop/delete/id-bioskop/{idBioskop}")
//    public String deteleBioskop(
//            @PathVariable(value = "idBioskop") String idBioskop,
//            Model model
//    ) {
//        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
//
//        if (bioskopModel == null) {
//            return "no-bioskop";
//        }
//        // Remove bioskop menggunakan method remove di Service
//        bioskopService.removeBioskop(bioskopModel);
//
//        // Add variabel BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
//        model.addAttribute("bioskop", bioskopModel);
//        return "delete-bioskop";
//    }
//}
