package br.com.RoutiNATOR.util;

import br.com.RoutiNATOR.Repository.TagsRepository;
import br.com.RoutiNATOR.Repository.TarefaRepository;
import br.com.RoutiNATOR.model.Tarefa;

import java.util.ArrayList;

public class MockData {

    private final TarefaRepository tarefasRepository;
    private final TagsRepository tags;

    public MockData(
            TarefaRepository tarefasRepository,
            TagsRepository tags
    ) {
        this.tarefasRepository = tarefasRepository;
        this.tags = tags;
    }

    public void popularDadosTeste()
    {

        // =========================
        // CRIAÇÃO DAS TAGS
        // =========================

        tags.put("estudos", new ArrayList<>());
        tags.put("trabalho", new ArrayList<>());
        tags.put("saúde", new ArrayList<>());
        tags.put("projeto", new ArrayList<>());
        tags.put("urgente", new ArrayList<>());



        // =========================
        // CRIAÇÃO DAS TAREFAS
        // =========================

        Tarefa t1 = new Tarefa(
                "Estudar Collections",
                "Revisar HashMap e ArrayList",
                2
        );

        Tarefa t2 = new Tarefa(
                "Treino de pernas",
                "Fazer academia as 18h",
                1
        );

        Tarefa t3 = new Tarefa(
                "Implementar tags",
                "Adicionar sistema de tags ao RoutiNATOR",
                3
        );

        Tarefa t4 = new Tarefa(
                "Enviar currículo",
                "Aplicar para vagas de estágio",
                4
        );

        Tarefa t5 = new Tarefa(
                "Comprar comida",
                "Comprar arroz, feijão e ovos",
                0
        );



        // =========================
        // ADICIONA NO HASHMAP
        // =========================

        tarefasRepository.put(t1);
        tarefasRepository.put(t2);
        tarefasRepository.put(t3);
        tarefasRepository.put(t4);
        tarefasRepository.put(t5);



        // =========================
        // ADICIONA TAGS NAS TAREFAS
        // =========================

        t1.setTags("estudos");

        t2.setTags("saúde");

        t3.setTags("projeto");
        t3.setTags("estudos");

        t4.setTags("trabalho");
        t4.setTags("urgente");

        t5.setTags("urgente");



        // =========================
        // ADICIONA TAREFAS NAS TAGS
        // =========================

        tags.get("estudos").add(t1);
        tags.get("estudos").add(t3);

        tags.get("saúde").add(t2);

        tags.get("projeto").add(t3);

        tags.get("trabalho").add(t4);

        tags.get("urgente").add(t4);
        tags.get("urgente").add(t5);



        System.out.println("Mock data carregado!");
    }
}
