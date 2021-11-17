package apap.tutorial.cineplux.restcontroller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.PenjagaDB;
import apap.tutorial.cineplux.service.BioskopRestService;
import apap.tutorial.cineplux.service.PenjagaRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class PenjagaRestController {
    @Autowired
    private PenjagaRestService penjagaRestService;

    @Autowired
    private PenjagaDB penjagaDB;

    @PostMapping(value="/penjaga")
    private ResponseEntity createPenjaga(@Valid @RequestBody PenjagaModel penjaga, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            penjagaRestService.createPenjaga(penjaga);
            return ResponseEntity.ok("Create penjaga success");
        }
    }

    @PutMapping(value="/penjaga/{noPenjaga}")
    private ResponseEntity updatePenjaga(@PathVariable("noPenjaga") Long noPenjaga, @RequestBody PenjagaModel penjaga) {
        try {
            penjagaRestService.updatePenjaga(noPenjaga, penjaga);
            return ResponseEntity.ok("Update penjaga success");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Penjaga with No Penjaga " + String.valueOf(noPenjaga) + " Not Found."
            );
        }
    }

    @GetMapping(value="/penjaga/{noPenjaga}")
    private PenjagaModel retrievePenjaga(@PathVariable("noPenjaga") Long noPenjaga) {
        try {
            return penjagaRestService.getPenjagaByNoPenjaga(noPenjaga);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Penjaga " + String.valueOf(noPenjaga) + " Not Found."
            );
        }
    }
    @GetMapping(value = "/list-penjaga")
    private List<PenjagaModel> retrieveListPenjaga() {
        return penjagaRestService.retrieveListPenjaga();
    }

    @DeleteMapping(value="/penjaga/{noPenjaga}")
    private ResponseEntity deletePenjaga(@PathVariable("noPenjaga") Long noPenjaga) {
        try {
            penjagaRestService.deletePenjaga(noPenjaga);
            return ResponseEntity.ok("Penjaga with No Penjaga " + String.valueOf(noPenjaga) + " Deleted!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Penjaga with No Penjaga " + String.valueOf(noPenjaga) + " Not Found."
            );
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bioskop is still open!"
            );
        }
    }
    @GetMapping(value="/penjaga/umur/{noPenjaga}")
    private PenjagaModel predictUmurPenjaga(@PathVariable("noPenjaga") Long noPenjaga) {
        try {
            PenjagaModel penjaga = penjagaRestService.getPenjagaByNoPenjaga(noPenjaga);
            Integer umur = penjagaRestService.umurPenjaga(noPenjaga).block().getAge();
            penjaga.setUmur(umur);
            return penjagaDB.save(penjaga);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Penjaga " + String.valueOf(noPenjaga) + " Not Found."
            );
        }
    }
}
