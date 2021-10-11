package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.PenjagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
public class PenjagaController {
    @Qualifier("penjagaServiceImpl")
    @Autowired
    PenjagaService penjagaService;

    @Qualifier("bioskopServiceImpl")
    @Autowired
    BioskopService bioskopService;

    @GetMapping("/penjaga/add/{noBioskop}")
    public String addPenjagaForm(@PathVariable Long noBioskop, Model model) {
        PenjagaModel penjaga = new PenjagaModel();
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        penjaga.setBioskop(bioskop);
        model.addAttribute("penjaga", penjaga);
        return "form-add-penjaga";
    }

    @PostMapping("/penjaga/add")
    public String addPenjagaSubmit(
            @ModelAttribute PenjagaModel penjaga,
            Model model
    ) {
        penjagaService.addPenjaga(penjaga);
        model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
        model.addAttribute("namaPenjaga", penjaga.getNamaPenjaga());
        return "add-penjaga";
    }
    @GetMapping("penjaga/update/{noPenjaga}")
    public String updatePenjagaForm(@PathVariable Long noPenjaga, Model model) {
        PenjagaModel penjaga = penjagaService.getPenjagaByNoPenjaga(noPenjaga);
        if (penjaga == null) {
            return "no-penjaga";
        }
        model.addAttribute("penjaga", penjaga);
        return "form-update-penjaga";
    }
    @PostMapping("penjaga/update")
    public String updatePenjagaSubmit(@ModelAttribute PenjagaModel penjaga, Model model){
        boolean penjagaUpdate = penjagaService.updatePenjaga(penjaga);
        String result = "";
        if (penjagaUpdate == false) {
            result += "Penjaga gagal diupdate, bioskop masih buka!";
        } else {
            result += "Penjaga berhasil diupdate";
        }
        model.addAttribute("result", result);
        return "update-penjaga";
    }

    @GetMapping("/penjaga/delete/{noPenjaga}")
    public String removePenjagaByNoPenjaga(
            @PathVariable Long noPenjaga,
            @ModelAttribute PenjagaModel penjagaModel,
            Model model
    ) {
        PenjagaModel penjaga = penjagaService.getPenjagaByNoPenjaga(noPenjaga);
        if (penjaga == null) {
            return "no-penjaga";
        }
        LocalTime now = LocalTime.now();
        LocalTime openTime = penjaga.getBioskop().getWaktuBuka();
        LocalTime closedTime = penjaga.getBioskop().getWaktuTutup();

        if (now.isBefore(openTime) || now.isAfter(closedTime)) {
            penjagaService.deletePenjaga(penjaga);
            return "delete-penjaga-sukses";
        } else {
            return "delete-penjaga-fail";
        }
    }
    @PostMapping("/penjaga/delete")
    public String deletePenjagaSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        model.addAttribute("noBioskop", bioskop.getNoBioskop());
        int res = 1;
        for (PenjagaModel penjaga:
                bioskop.getListPenjaga()) {
            res = penjagaService.deletePenjaga(penjaga);
        }
        if (res == 1){
            return "delete-penjaga";
        }
        return "error";
    }
}
