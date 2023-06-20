package com.bdv.microservicios.Msvcchequesliberados.model.repository;

import com.bdv.microservicios.Msvcchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.Msvcchequesliberados.model.entity.ChequeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ChequeRepo extends JpaRepository<Cheque, ChequeId> {
    @Query(value="SELECT u FROM TBLCHEQUES u WHERE u.estatus=?1 and u.agencia=?2")
    List<Cheque> findByEstatusAndAgencia(String estatus, String agencia);

}
