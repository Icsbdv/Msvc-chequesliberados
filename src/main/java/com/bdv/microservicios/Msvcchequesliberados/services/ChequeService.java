package com.bdv.microservicios.Msvcchequesliberados.services;

import com.bdv.microservicios.Msvcchequesliberados.model.entity.Cheque;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ChequeService {
    List<Cheque> consultarChequesPorAgenciaYEstatus(String status,String agencia);
}
