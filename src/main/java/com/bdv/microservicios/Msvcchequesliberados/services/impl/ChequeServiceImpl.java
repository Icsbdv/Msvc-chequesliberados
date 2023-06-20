package com.bdv.microservicios.Msvcchequesliberados.services.impl;

import com.bdv.microservicios.Msvcchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.Msvcchequesliberados.model.repository.ChequeRepo;
import com.bdv.microservicios.Msvcchequesliberados.services.ChequeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepo chequeRepo;

    public ChequeServiceImpl(ChequeRepo chequeRepo) {
        this.chequeRepo = chequeRepo;
    }

    @Override
    public List<Cheque> consultarChequesPorAgenciaYEstatus(String status, String agencia) {
        List<Cheque> chequeList=chequeRepo.findByEstatusAndAgencia(status, agencia);

            return chequeList;
        }
    }

