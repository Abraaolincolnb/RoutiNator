package br.com.RoutiNATOR.Repository;

import br.com.RoutiNATOR.model.Tarefa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TarefaRepository {
    private final Map<String, Tarefa> tarefas = new HashMap<>(); // O HashMap que guarda as tarefas do usuario

    public void put(Tarefa tarefa) {
        tarefas.put(tarefa.getNome(), tarefa);
    }

    public Tarefa get(String nome) {
        return tarefas.get(nome);
    }


    public Collection<Tarefa> listar() {
        return tarefas.values();
    }

    public Tarefa remove(String nome) {
        return tarefas.remove(nome);
    }

    public boolean vazia() {
        return tarefas.isEmpty();
    }

    public boolean exist(String nome) {
        return tarefas.containsKey(nome);
    }
}
