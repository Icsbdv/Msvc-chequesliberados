package com.bdv.microservicios.msvchequesliberados.model.mappers;


import com.bdv.microservicios.msvchequesliberados.model.dto.Chequedto;
import com.bdv.microservicios.msvchequesliberados.model.entity.Cheque;

import java.util.function.Function;

public class ChequeToChequedtoMapper implements Function<Cheque, Chequedto> {
    @Override
    public Chequedto apply(Cheque cheque) {
        Chequedto chequedto=new Chequedto(cheque.getChequeId().getSerial(),
                                          cheque.getChequeId().getBanco(),
                                          cheque.getChequeId().getOficina(),
                                          cheque.getChequeId().getDv(),
                                          cheque.getChequeId().getCuenta(),
                                          cheque.getFechacontable(),
                                          cheque.getCodBanco(),
                                            cheque.getAgencia(),
                                            cheque.getCajero(),
                                            cheque.getDin(),
                                            cheque.getTrancode(),
                                            cheque.getMonto(),
                                            cheque.getCorregido(),
                                            cheque.getTransmitido(),
                                            cheque.getCuentadepo(),
                                            cheque.getSerialp(),
                                            cheque.getDna(),
                                            cheque.getEstatus(),
                                            cheque.getAuxiliar1(),
                                            cheque.getAuxiliar2(),
                                            cheque.getAuxiliar3(),
                                            cheque.getAuxiliar4(),
                                            cheque.getAuxiliar5(),
                                            cheque.getTransmitidooracle());


        return chequedto;
    }
}
