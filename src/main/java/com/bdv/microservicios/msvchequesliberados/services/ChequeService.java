package com.bdv.microservicios.msvchequesliberados.services;

import com.bdv.microservicios.msvchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.msvchequesliberados.model.entity.ChequeId;

import java.util.List;
import java.util.Optional;


public interface ChequeService {
    List<Cheque> consultarChequesPorAgenciaYEstatus(String estatus,String agencia);
    List<Cheque> consultarChequesAgencia(String agencia);
    Optional<Cheque> consultarChequePorId(ChequeId chequeId);
    Optional<Cheque> actualizarestatuscheque(ChequeId chequeId,String nuevoEstatus);
}
