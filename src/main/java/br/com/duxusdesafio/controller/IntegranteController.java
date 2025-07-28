package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/integrantes")
public class IntegranteController {

    private static final List<Integrante> integrantes = new ArrayList<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Integrante> cadastrarIntegrante(@RequestBody Integrante integrante) {
        integrante.setId(idGenerator.getAndIncrement());
        integrantes.add(integrante);
        return new ResponseEntity<>(integrante, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Integrante>> listarIntegrantes() {
        return ResponseEntity.ok(integrantes);
    }
} 