package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.FilmModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.FilmService;
import apap.tutorial.cineplux.service.PenjagaService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

@Controller
public class BioskopController {
    @Qualifier("bioskopServiceImpl")
    @Autowired
    private BioskopService bioskopService;

    @Qualifier("filmServiceImpl")
    @Autowired
    private FilmService filmService;

    @GetMapping("/bioskop/add")
    public String addBioskopForm(Model model) {
        List<FilmModel> listFilm = filmService.getListFilm();
        model.addAttribute("bioskop", new BioskopModel());
        model.addAttribute("listFilm", listFilm);
        return "form-add-bioskop";
    }

    @PostMapping(value= "/bioskop/add", params = {"save"})
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
//    List<FilmModel> listFilm = filmService.findByOrderByNamaFilmAsc();
    model.addAttribute("bioskop", bioskop);
    model.addAttribute("listPenjaga", listPenjaga);
    model.addAttribute("listFilm", bioskop.getListFilm());
//    model.addAttribute("listFilm", listFilm);
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

    @PostMapping(value = "/bioskop/add", params = {"addRow"})
    public String addRow(
            @ModelAttribute BioskopModel bioskop,
            BindingResult bindingResult,
            Model model
    ) {
        List<FilmModel> listFilm = filmService.getListFilm();
        if (bioskop.getListFilm() == null) {
            bioskop.setListFilm(new ArrayList<FilmModel>());
        }
        List<FilmModel> newListFilm = bioskop.getListFilm();
        newListFilm.add(new FilmModel());
//        FilmModel newFilm = new FilmModel();
//        newListFilm.add(newFilm);
//        if(listFilm.isEmpty()) {
//            listFilm = new ArrayList<>();
//        }
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("listFilm", listFilm);
        return "form-add-bioskop";
    }

    @PostMapping(value = "/bioskop/add", params = {"deleteRow"})
    public String deleteRow(
            @ModelAttribute BioskopModel bioskop,
            final HttpServletRequest request,
            BindingResult bindingResult,
            Model model
    ){
        List<FilmModel> listFilm = filmService.getListFilm();

        final Integer rownum = Integer.valueOf(request.getParameter("deleteRow"));
        bioskop.getListFilm().remove(rownum.intValue());
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("listFilm", listFilm);
        return "form-add-bioskop";
    }


}

