package br.com.RoutiNATOR;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockData {

    public static void popularDadosTeste(
            Map<String, Tarefa> tarefas,
            Map<String, List<Tarefa>> tags
    ) {

        // =========================
        // CRIAÇÃO DAS TAGS
        // =========================

        tags.put("estudos", new ArrayList<>());
        tags.put("trabalho", new ArrayList<>());
        tags.put("saude", new ArrayList<>());
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

        tarefas.put(t1.getNome(), t1);
        tarefas.put(t2.getNome(), t2);
        tarefas.put(t3.getNome(), t3);
        tarefas.put(t4.getNome(), t4);
        tarefas.put(t5.getNome(), t5);



        // =========================
        // ADICIONA TAGS NAS TAREFAS
        // =========================

        t1.setTags("estudos");

        t2.setTags("saude");

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

        tags.get("saude").add(t2);

        tags.get("projeto").add(t3);

        tags.get("trabalho").add(t4);

        tags.get("urgente").add(t4);
        tags.get("urgente").add(t5);



        System.out.println("Mock data carregado!");
    }
}
