package com.bdv.microservicios.Msvcchequesliberados.controllers;

import com.bdv.microservicios.Msvcchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.Msvcchequesliberados.services.ChequeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app/cheques")
public class ChequeController {
    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @GetMapping
    public ResponseEntity<List<Cheque>> consultarcheques(@RequestParam String agencia,@RequestParam(required = false) String status){

        ResponseEntity<List<Cheque>> response;

        List<Cheque> cheques=chequeService.consultarChequesPorAgenciaYEstatus(status,agencia);

        if (!cheques.isEmpty()){
            response=new ResponseEntity<>(cheques, HttpStatus.OK);
        }else{
            response=ResponseEntity.notFound().build();
        }

        return response;
    }

}
