package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/processamento")
public class ProcessamentoController {

    private final ApiService apiService;

    @Autowired
    public ProcessamentoController(ApiService apiService) {
        this.apiService = apiService;
    }

    // Utiliza os dados em memória dos outros controllers
    private List<Time> getAllTimes() {
        return TimeController.times;
    }

    @GetMapping("/time-da-data")
    public ResponseEntity<Time> timeDaData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(apiService.timeDaData(data, getAllTimes()));
    }

    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<Integrante> integranteMaisUsado(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        return ResponseEntity.ok(apiService.integranteMaisUsado(dataInicial, dataFinal, getAllTimes()));
    }

    @GetMapping("/time-mais-comum")
    public ResponseEntity<List<String>> integrantesDoTimeMaisComum(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        return ResponseEntity.ok(apiService.integrantesDoTimeMaisComum(dataInicial, dataFinal, getAllTimes()));
    }

    @GetMapping("/funcao-mais-comum")
    public ResponseEntity<String> funcaoMaisComum(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        return ResponseEntity.ok(apiService.funcaoMaisComum(dataInicial, dataFinal, getAllTimes()));
    }

    @GetMapping("/franquia-mais-famosa")
    public ResponseEntity<String> franquiaMaisFamosa(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        return ResponseEntity.ok(apiService.franquiaMaisFamosa(dataInicial, dataFinal, getAllTimes()));
    }

    @GetMapping("/contagem-por-franquia")
    public ResponseEntity<Map<String, Long>> contagemPorFranquia(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        return ResponseEntity.ok(apiService.contagemPorFranquia(dataInicial, dataFinal, getAllTimes()));
    }

    @GetMapping("/contagem-por-funcao")
    public ResponseEntity<Map<String, Long>> contagemPorFuncao(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        return ResponseEntity.ok(apiService.contagemPorFuncao(dataInicial, dataFinal, getAllTimes()));
    }
} 