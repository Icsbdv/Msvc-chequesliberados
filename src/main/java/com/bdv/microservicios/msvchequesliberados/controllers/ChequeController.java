package com.bdv.microservicios.msvchequesliberados.controllers;

import com.bdv.microservicios.msvchequesliberados.model.dto.Chequedto;
import com.bdv.microservicios.msvchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.msvchequesliberados.model.entity.ChequeId;
import com.bdv.microservicios.msvchequesliberados.model.mappers.ChequeToChequedtoMapper;
import com.bdv.microservicios.msvchequesliberados.services.ChequeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("app/cheques")
public class ChequeController {
    private final ChequeService chequeService;
    List<Cheque> cheques;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @GetMapping
    public ResponseEntity<List<Chequedto>> consultarcheques(@RequestParam(required = false) String estatus, @RequestParam(required = false) String agencia){
        ResponseEntity<List<Chequedto>> response;
        if(agencia!=null) {
            if (estatus != null) {
                cheques = chequeService.consultarChequesPorAgenciaYEstatus(estatus, agencia);
            } else {
                cheques = chequeService.consultarChequesAgencia(agencia);
            }
            if (!cheques.isEmpty()) {
                List<Chequedto> chequedtos = cheques.stream().map(cheque ->
                        new ChequeToChequedtoMapper().apply(cheque)).toList();
                response = new ResponseEntity<>(chequedtos, HttpStatus.OK);
            } else {
                response = ResponseEntity.notFound().build();
            }
        }else{
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @PostMapping("/{nuevoestatus}")
    public ResponseEntity<Chequedto> actualizarestatuscheque(@RequestBody ChequeId chequeId, @PathVariable String nuevoestatus){
        Optional<Cheque> chequeactualizado;
        if(chequeId!=null){
            Optional<Cheque> optionalCheque=chequeService.consultarChequePorId(chequeId);
            if (optionalCheque.isPresent()){
                if(optionalCheque.get().getChequeId().getBanco().equals("0102")) {
                    chequeactualizado = chequeService.actualizarestatuscheque(chequeId, nuevoestatus);
                    if (chequeactualizado.isPresent()) {
                        return new ResponseEntity<>(new ChequeToChequedtoMapper().apply(chequeactualizado.get()), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
                    }
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
                }
            }else{
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
