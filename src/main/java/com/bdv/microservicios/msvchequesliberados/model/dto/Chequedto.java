package com.bdv.microservicios.msvchequesliberados.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Getter
@Setter
public class Chequedto {
    String serial;
    String banco;
    String oficina;
    String dv;
    String cuenta;
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

    public Chequedto(String serial, String banco, String oficina, String dv, String cuenta, String fechacontable, String codBanco, String agencia, String cajero, String din, String trancode, String monto, /*String front, String rear,*/ String corregido, String transmitido, String cuentadepo, String serialp, String dna, String estatus, String auxiliar1, String auxiliar2, String auxiliar3, String auxiliar4, String auxiliar5, String transmitidooracle) {
        this.serial = serial;
        this.banco = banco;
        this.oficina = oficina;
        this.dv = dv;
        this.cuenta = cuenta;
        this.fechacontable = fechacontable;
        this.codBanco = codBanco;
        this.agencia = agencia;
        this.cajero = cajero;
        this.din = din;
        this.trancode = trancode;
        this.monto = monto;
       // this.front = front;
       // this.rear = rear;
        this.corregido = corregido;
        this.transmitido = transmitido;
        this.cuentadepo = cuentadepo;
        this.serialp = serialp;
        this.dna = dna;
        this.estatus = estatus;
        this.auxiliar1 = auxiliar1;
        this.auxiliar2 = auxiliar2;
        this.auxiliar3 = auxiliar3;
        this.auxiliar4 = auxiliar4;
        this.auxiliar5 = auxiliar5;
        this.transmitidooracle = transmitidooracle;
    }
}
