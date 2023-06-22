package com.bdv.microservicios.msvchequesliberados.model.repository;

import com.bdv.microservicios.msvchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.msvchequesliberados.model.entity.ChequeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface ChequeRepo extends JpaRepository<Cheque, ChequeId> {
    @Query(value="SELECT u FROM TBLCHEQUES u WHERE u.estatus=?1 and u.agencia=?2")
    List<Cheque> findByEstatusAndAgencia(String estatus, String agencia);

    @Query(value="SELECT u FROM TBLCHEQUES u WHERE u.agencia=?1")
    List<Cheque> findByAgencia(String agencia);

}
