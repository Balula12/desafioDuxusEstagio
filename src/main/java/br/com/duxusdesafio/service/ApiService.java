package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import br.com.duxusdesafio.model.ComposicaoTime;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService {

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        if (data == null || todosOsTimes == null) {
            return null;
        }
        // Procura o time cuja data bate com a informada
        for (Time time : todosOsTimes) {
            if (data.equals(time.getData())) {
                return time;
            }
        }
        return null;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return null;
        }
        Map<Integrante, Integer> contagem = new HashMap<>();
        for (Time time : todosOsTimes) {
            LocalDate data = time.getData();
            if ((dataInicial == null || !data.isBefore(dataInicial)) && (dataFinal == null || !data.isAfter(dataFinal))) {
                if (time.getComposicaoTime() != null) {
                    for (ComposicaoTime comp : time.getComposicaoTime()) {
                        Integrante integrante = comp.getIntegrante();
                        contagem.put(integrante, contagem.getOrDefault(integrante, 0) + 1);
                    }
                }
            }
        }
        Integrante maisUsado = null;
        int max = 0;
        for (Map.Entry<Integrante, Integer> entry : contagem.entrySet()) {
            if (maisUsado == null || entry.getValue() > max) {
                maisUsado = entry.getKey();
                max = entry.getValue();
            }
        }
        return maisUsado;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return null;
        }
        Map<Set<Long>, Integer> contagem = new HashMap<>();
        Map<Set<Long>, List<Integrante>> composicoes = new HashMap<>();
        for (Time time : todosOsTimes) {
            LocalDate data = time.getData();
            if ((dataInicial == null || !data.isBefore(dataInicial)) && (dataFinal == null || !data.isAfter(dataFinal))) {
                if (time.getComposicaoTime() != null) {
                    Set<Long> idsIntegrantes = new HashSet<>();
                    List<Integrante> integrantes = new ArrayList<>();
                    for (ComposicaoTime comp : time.getComposicaoTime()) {
                        Integrante integrante = comp.getIntegrante();
                        idsIntegrantes.add(integrante.getId());
                        integrantes.add(integrante);
                    }
                    contagem.put(idsIntegrantes, contagem.getOrDefault(idsIntegrantes, 0) + 1);
                    composicoes.put(idsIntegrantes, integrantes);
                }
            }
        }
        Set<Long> maisComum = null;
        int max = 0;
        for (Map.Entry<Set<Long>, Integer> entry : contagem.entrySet()) {
            if (maisComum == null || entry.getValue() > max) {
                maisComum = entry.getKey();
                max = entry.getValue();
            }
        }
        if (maisComum == null) return null;
        List<Integrante> integrantes = composicoes.get(maisComum);
        List<String> nomes = new ArrayList<>();
        if (integrantes != null) {
            for (Integrante i : integrantes) {
                nomes.add(i.getNome());
            }
        }
        return nomes;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return null;
        }
        Map<String, Integer> contagem = new HashMap<>();
        for (Time time : todosOsTimes) {
            LocalDate data = time.getData();
            if ((dataInicial == null || !data.isBefore(dataInicial)) && (dataFinal == null || !data.isAfter(dataFinal))) {
                if (time.getComposicaoTime() != null) {
                    for (ComposicaoTime comp : time.getComposicaoTime()) {
                        Integrante integrante = comp.getIntegrante();
                        String funcao = integrante.getFuncao();
                        contagem.put(funcao, contagem.getOrDefault(funcao, 0) + 1);
                    }
                }
            }
        }
        String maisComum = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : contagem.entrySet()) {
            if (maisComum == null || entry.getValue() > max) {
                maisComum = entry.getKey();
                max = entry.getValue();
            }
        }
        return maisComum;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return null;
        }
        Map<String, Integer> contagem = new HashMap<>();
        for (Time time : todosOsTimes) {
            LocalDate data = time.getData();
            if ((dataInicial == null || !data.isBefore(dataInicial)) && (dataFinal == null || !data.isAfter(dataFinal))) {
                if (time.getComposicaoTime() != null) {
                    for (ComposicaoTime comp : time.getComposicaoTime()) {
                        Integrante integrante = comp.getIntegrante();
                        String franquia = integrante.getFranquia();
                        contagem.put(franquia, contagem.getOrDefault(franquia, 0) + 1);
                    }
                }
            }
        }
        String maisFamosa = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : contagem.entrySet()) {
            if (maisFamosa == null || entry.getValue() > max) {
                maisFamosa = entry.getKey();
                max = entry.getValue();
            }
        }
        return maisFamosa;
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Long> contagem = new HashMap<>();
        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return contagem;
        }
        for (Time time : todosOsTimes) {
            LocalDate data = time.getData();
            if ((dataInicial == null || !data.isBefore(dataInicial)) && (dataFinal == null || !data.isAfter(dataFinal))) {
                if (time.getComposicaoTime() != null) {
                    for (ComposicaoTime comp : time.getComposicaoTime()) {
                        Integrante integrante = comp.getIntegrante();
                        String franquia = integrante.getFranquia();
                        contagem.put(franquia, contagem.getOrDefault(franquia, 0L) + 1L);
                    }
                }
            }
        }
        return contagem;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Long> contagem = new HashMap<>();
        if (todosOsTimes == null || todosOsTimes.isEmpty()) {
            return contagem;
        }
        for (Time time : todosOsTimes) {
            LocalDate data = time.getData();
            if ((dataInicial == null || !data.isBefore(dataInicial)) && (dataFinal == null || !data.isAfter(dataFinal))) {
                if (time.getComposicaoTime() != null) {
                    for (ComposicaoTime comp : time.getComposicaoTime()) {
                        Integrante integrante = comp.getIntegrante();
                        String funcao = integrante.getFuncao();
                        contagem.put(funcao, contagem.getOrDefault(funcao, 0L) + 1L);
                    }
                }
            }
        }
        return contagem;
    }

}
