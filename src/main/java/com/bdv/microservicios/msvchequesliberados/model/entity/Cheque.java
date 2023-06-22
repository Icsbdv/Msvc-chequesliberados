package com.bdv.microservicios.msvchequesliberados.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="TBLCHEQUES")
public class Cheque implements Serializable {
    @EmbeddedId
    private ChequeId chequeId;
    String fechacontable;
    String codBanco;
    String agencia;
    String cajero;
    String din;
    String trancode;
    String monto;
    String corregido;
    String transmitido;
    String cuentadepo;
    String serialp;
    String dna;
    String estatus;
    String auxiliar1;
    String auxiliar2;
    String auxiliar3;
    String auxiliar4;
    String auxiliar5;
    String transmitidooracle;
}
