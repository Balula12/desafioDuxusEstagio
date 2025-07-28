package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/times")
public class TimeController {

    public static final List<Time> times = new ArrayList<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Time> cadastrarTime(@RequestBody TimeRequest request) {
        Time time = new Time();
        time.setId(idGenerator.getAndIncrement());
        time.setData(request.getData());
        List<ComposicaoTime> composicao = new ArrayList<>();
        for (Integrante integrante : request.getIntegrantes()) {
            composicao.add(new ComposicaoTime(time, integrante));
        }
        time.setComposicaoTime(composicao);
        times.add(time);
        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Time>> listarTimes() {
        return ResponseEntity.ok(times);
    }

    // DTO para receber o cadastro do time
    public static class TimeRequest {
        private LocalDate data;
        private List<Integrante> integrantes;

        public LocalDate getData() { return data; }
        public void setData(LocalDate data) { this.data = data; }
        public List<Integrante> getIntegrantes() { return integrantes; }
        public void setIntegrantes(List<Integrante> integrantes) { this.integrantes = integrantes; }
    }
} 