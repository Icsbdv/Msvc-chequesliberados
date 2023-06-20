package com.bdv.microservicios.Msvcchequesliberados.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class ChequeId implements Serializable {
    String serial;
    String banco;
    String oficina;
    String dv;
    String cuenta;
}
