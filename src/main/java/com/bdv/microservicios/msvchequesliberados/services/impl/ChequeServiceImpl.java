package com.bdv.microservicios.msvchequesliberados.services.impl;

import com.bdv.microservicios.msvchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.msvchequesliberados.model.entity.ChequeId;
import com.bdv.microservicios.msvchequesliberados.model.repository.ChequeRepo;
import com.bdv.microservicios.msvchequesliberados.services.ChequeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepo chequeRepo;
    Cheque chequeActualizado;

    public ChequeServiceImpl(ChequeRepo chequeRepo) {
        this.chequeRepo = chequeRepo;
    }

    @Override
    public List<Cheque> consultarChequesPorAgenciaYEstatus(String estatus, String agencia) {
        List<Cheque> chequeList=chequeRepo.findByEstatusAndAgencia(estatus, agencia);
        return chequeList;
    }

    @Override
    public List<Cheque> consultarChequesAgencia(String agencia) {
        return chequeRepo.findByAgencia(agencia);
    }

    @Override
    public Optional<Cheque> consultarChequePorId(ChequeId chequeId) {
        return chequeRepo.findById(chequeId);
    }

    @Override
    public Optional<Cheque> actualizarestatuscheque(ChequeId chequeId, String nuevoEstatus) {
        Optional<Cheque> chequeGuardado=chequeRepo.findById(chequeId);
        if (chequeGuardado.isPresent()){
            chequeGuardado.get().setEstatus(nuevoEstatus);
            chequeActualizado=chequeRepo.save(chequeGuardado.get());
        }
        return Optional.ofNullable(chequeActualizado);
    }
}

