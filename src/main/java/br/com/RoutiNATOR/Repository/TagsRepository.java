package br.com.RoutiNATOR.Repository;

import br.com.RoutiNATOR.model.Tarefa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagsRepository {
    private final Map<String, List<Tarefa>> tags = new HashMap<>();
    private final TarefaRepository repository = new TarefaRepository();

    public List<Tarefa> get(String nome) {
        return tags.get(nome);
    }

    public boolean containsKey(String nome) {
        return tags.containsKey(nome);
    }

    public void put(String nome, List a) {
        tags.put(nome, a);
    }

    public void procurarERemover(String tarefaInput) {
        for (String s : tags.keySet()) {
            if (tags.get(s).contains(repository.get(tarefaInput))) {
                tags.get(s).remove(repository.get(tarefaInput));
            }
        }
    }

    public void ListarTags() {
        for (String s : tags.keySet()) {
            System.out.println(s);
        }
    }

    public void add(Tarefa tarefaInput) { tags.put(tarefaInput.getNome(), tags.get(tarefaInput.getNome())); }
}
