package com.bdv.microservicios.msvchequesliberados.controllers;

import com.bdv.microservicios.msvchequesliberados.model.dto.Chequedto;
import com.bdv.microservicios.msvchequesliberados.model.entity.Cheque;
import com.bdv.microservicios.msvchequesliberados.model.entity.ChequeId;
import com.bdv.microservicios.msvchequesliberados.model.entity.security.AuthenticationRequest;
import com.bdv.microservicios.msvchequesliberados.model.entity.security.TokenInfo;
import com.bdv.microservicios.msvchequesliberados.model.mappers.ChequeToChequedtoMapper;
import com.bdv.microservicios.msvchequesliberados.services.ChequeService;
import com.bdv.microservicios.msvchequesliberados.services.security.IUsuarioService;
import com.bdv.microservicios.msvchequesliberados.services.security.JwtUtilService;
import com.bdv.microservicios.msvchequesliberados.services.security.UsuarioDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("app/cheques")
public class ChequeController {



    private final AuthenticationManager authenticationManager;

    private final UsuarioDetailsService usuarioDetailsService;

    private final JwtUtilService jwtService;
    private final ChequeService chequeService;

    private final IUsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(ChequeController.class);
    List<Cheque> cheques;

    public ChequeController(AuthenticationManager authenticationManager, UsuarioDetailsService usuarioDetailsService, JwtUtilService jwtService, ChequeService chequeService, IUsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtService = jwtService;
        this.chequeService = chequeService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("consultar")
    public ResponseEntity<List<Chequedto>> consultarcheques(@RequestParam(required = false) String estatus, @RequestParam(required = false) String agencia){
        ResponseEntity<List<Chequedto>> response;
        if(agencia!=null) {
            if (estatus != null) {
                cheques = chequeService.consultarChequesPorAgenciaYEstatus(estatus, agencia);
            } else {
                cheques = chequeService.consultarChequesAgencia(agencia);
            }
            if (!cheques.isEmpty()) {
                List<Chequedto> chequedtos = cheques.stream().map(cheque ->
                        new ChequeToChequedtoMapper().apply(cheque)).toList();
                response = new ResponseEntity<>(chequedtos, HttpStatus.OK);
            } else {
                response = ResponseEntity.notFound().build();
            }
        }else{
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @PostMapping("modificar/{nuevoestatus}")
    public ResponseEntity<Chequedto> actualizarestatuscheque(@RequestBody ChequeId chequeId, @PathVariable String nuevoestatus){
        Optional<Cheque> chequeactualizado;
        if(chequeId!=null){
            Optional<Cheque> optionalCheque=chequeService.consultarChequePorId(chequeId);
            if (optionalCheque.isPresent()){
                if(optionalCheque.get().getChequeId().getBanco().equals("0102")) {
                    chequeactualizado = chequeService.actualizarestatuscheque(chequeId, nuevoestatus);
                    if (chequeactualizado.isPresent()) {
                        return new ResponseEntity<>(new ChequeToChequedtoMapper().apply(chequeactualizado.get()), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
                    }
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
                }
            }else{
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        logger.info("Autenticando al usuario {}",authenticationRequest.getUsuario());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsuario(),
                        authenticationRequest.getClave()));

        final UserDetails userDetails=usuarioDetailsService.loadUserByUsername(authenticationRequest.getUsuario());

        final String jwt = jwtService.generateToken(userDetails);

        TokenInfo tokenInfo=new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);

    }

}
