package br.com.RoutiNATOR;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class RoutiNatorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RoutiNatorApplication.class);
    }
    /// INDEXAÇÃO DOS OBJETOS
    Scanner scanner = new Scanner(System.in); //o scanner utilizado em tudo
    Map<String, Tarefa> tarefas = new HashMap<>(); // O HashMap que guarda as tarefas do usuario
    Map<String, List<Tarefa>> tags = new HashMap<>();

    public void uiMenu() {
        System.out.flush();
        System.out.println("""
                  %s_____             _   _ _   _       _______ ____  _____ \s
                 |  __ \\           | | (_) \\ | |   /\\|__   __/ __ \\|  __ \\\s
                 | |__) |___  _   _| |_ _|  \\| |  /  \\  | | | |  | | |__) |
                 |  _  // _ \\| | | | __| | . ` | / /\\ \\ | | | |  | |  _  /\s
                 | | \\ \\ (_) | |_| | |_| | |\\  |/ ____ \\| | | |__| | | \\ \\\s
                 |_|  \\_\\___/ \\__,_|\\__|_|_| \\_/_/    \\_\\_|  \\____/|_|  \\_\\
                 ##########################################################%s
                """.formatted(Color.AZUL, Color.RESET));
    }


    /// CRIA TAREFA
    public void criaTarefa() {

        // Pede dados ao usuário para criação de tarefa
        System.out.println(Color.VERDE_BRIGHT + "CRIAÇÃO DE TAREFAS" + Color.RESET);
        System.out.println("Digite o nome da tarefa: ");
        String nome = scanner.nextLine();

        // Volta ao menu principal se a tarefa já existe
        if (tarefas.containsKey(nome)) {
            System.out.println("Tarefa já existe! voltando ao menu principal...");
            return;
        }

        System.out.println("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite a prioridade da tarefa sendo 0 o menos importante (apenas numeros inteiros): ");
        int prioridade = Integer.parseInt(scanner.nextLine());

        Tarefa tarefa = new Tarefa(nome, descricao, prioridade);

        // Adiciona tarefa no hashmap tarefas
        tarefas.put(nome, tarefa);
    }


    /// BUSCA TAREFA
        public String consultarTarefa(){

        // Utiliza Foreach para imprimir cada tarefa com seu estado de conclusão
        tarefas.forEach((nome, tarefa) -> {
            System.out.println(nome + " - Concluido: " + tarefa.isConcluido());
        });

        // Pede ao usuário que digite uma tarefa para que seus atributos sejam imprimidos
        System.out.println("Digite a tarefa desejada");
        String tarefaInput = scanner.nextLine();
        Tarefa tarefaBuscada = tarefas.get(tarefaInput);
        System.out.println(tarefaBuscada);

        return tarefaInput;
    }

    /// MENU CONSULTA
    public void menuConsulta(String tarefaInput){
        System.out.println("""
                1 - Alterar tarefa
                2 - Remover tarefa
                3 - Voltar ao menu principal
                """);

        switch (scanner.nextLine()) {

            case "1": // Alterar tarefas
                alterarTarefa(tarefaInput);
                break;

            case "2": // Remover tarefas
                System.out.println("Certeza? Digite Y para confirmar, qualquer outra tecla para cancelar");
                if (scanner.nextLine() .equals("Y")) {
                    for (String s : tags.keySet()) {
                        if (tags.get(s).contains(tarefas.get(tarefaInput))) {
                            tags.get(s).remove(tarefas.get(tarefaInput));
                        }
                    }
                    tarefas.remove(tarefaInput);
                    System.out.println("tarefa removida com sucesso!");
                } else {
                    System.out.println("Tarefa não foi deletada");
                }
                break;

            case "3":
                System.out.println("Voltando ao menu principal...");
                break;

            default:
                System.out.println("Opção inválida! Voltando ao menu principal...");
                break;
        }
    }

    public void alterarTarefa(String tarefaInput) {

        System.out.println("""
                O que você deseja alterar?
                1 - Nome
                2 - Descrição
                3 - Prioridade
                4 - Adicionar Tags
                5 - Voltar ao menu principal
                """);

        switch(scanner.nextLine()) {

            case "1": // altera nome
                System.out.println("Digite o novo nome que deseja atribuir para a tarefa: ");
                String novoNome = scanner.nextLine();

                for (String s : tarefas.keySet()) { // Verifica se o nome já pertence a outra tarefa
                    if (novoNome.equals(s)) {
                        System.out.println("Este nome já pertence a outra tarefa! Voltando ao menu principal...");
                        return;
                    }
                }

                // Salva a tarefa enquanto é removida
                // Depois altera o nome da tarefa salva e a adiciona de volta no hashmap de tarefas
                Tarefa tarefa = tarefas.remove(tarefaInput);
                tarefa.setNome(novoNome);
                tarefas.put(novoNome, tarefa);

                System.out.println("Tarefa renomeada com sucesso!");
                break;

            case "2": // Altera descrição
                System.out.println("Digite a nova descrição para a tarefa: ");
                tarefas.get(tarefaInput).setDescricao(scanner.nextLine());
                break;

            case "3": // Altera prioridade
                System.out.println("Digite a nova prioridade como um número igual ou maior a 0: ");

                // Verifica se o usuário digitou um inteiro. caso contrário, volta ao menu principal
                if (!scanner.hasNextInt()) {
                    System.out.println("Valor de prioridade inválida! Voltando ao menu principal...");
                    scanner.nextLine();
                    return;
                }
                int novaPrioridade = scanner.nextInt();
                scanner.nextLine();

                // Verifica se o usuário digitou um número de pelomenos 0. caso contrário, volta ao menu principal
                if (novaPrioridade < 0) {
                    System.out.println("Valor de prioridade menor que 0! Voltando ao menu principal...");
                    return;
                }
                tarefas.get(tarefaInput).setPrioridade(novaPrioridade);
                break;

            case "4": // Adiciona tags a uma tarefa e a mesma tarefa na lista da respectiva tag
                // Imprime todas as tags para o usuário
                for (String s : tags.keySet()) {
                    System.out.println(s);
                }

                System.out.println("Digite o nome da tag que deseja adicionar a esta tarefa: ");
                String tagParaAdicionar = scanner.nextLine();

                // Primeiro verifica se a tag existe
                // Depois verifica se a tarefa inserida já possui a tag desejada
                // Volta ao menu principal se um dos dois gerarem resultado indesejado
                if (!tags.containsKey(tagParaAdicionar)) {
                    System.out.println("Esta tag não existe! voltando ao menu principal...");
                    return;
                }
                if (tarefas.get(tarefaInput).getTags().contains(tagParaAdicionar)) {
                    System.out.println("Esta tarefa já possui a tag exigida! Voltando ao menu principal...");
                    return;
                }

                // Adiciona tag na tarefa desejada e depois adiciona a tarefa na tag
                tarefas.get(tarefaInput).setTags(tagParaAdicionar);
                tags.get(tagParaAdicionar).add(tarefas.get(tarefaInput));
                System.out.println("Tag adicionada!");
                break;

            case "5":
                System.out.println("Voltando ao menu principal...");
                break;

            default:
                System.out.println("Opção inválida! Voltando ao menu principal...");
                break;
        }
    }

    public void adicionarTag() {
        System.out.println("Digite o nome da tag que deseja adicionar: ");
        String novaTag = scanner.nextLine();

        // Verifica se a tag já existe. Se sim, volta ao menu principal, senão ela é adicionada no hashmap tags
        if (tags.containsKey(novaTag)) {
            System.out.println("Esta tag já existe! voltando ao menu principal...");
            return;
        }
        tags.put(novaTag, new ArrayList<>());
        System.out.println("Tarefa adicionada com sucesso!");

        // Verifica se a lista de tarefas está vazia.
        // Se não estiver, mostra ao usuário a lista de tarefas
        // e pergunta ao usuário se deseja adicionar a tag recentemente criada a uma tarefa.
        if (tarefas.isEmpty()) {
            return;
        }
        System.out.println("Lista de tarefas: ");
        for (String s : tarefas.keySet()) {
            System.out.println(s);
        }
        System.out.println("""
                Você deseja adicionar esta tag em alguma tarefa?
                1 - sim
                2 - não
                """);

        switch(scanner.nextLine()) {
            case "1": // Adicionar tag em tarefa
                System.out.println("Em que tarefa deseja adicionar esta tag?");
                String tarefaParaInserirTag = scanner.nextLine();

                // Verifica se a tarefa exigida existe. Se não, retorna ao menu principal
                if (!tarefas.containsKey(tarefaParaInserirTag)) {
                    System.out.println("Tarefa não existe! Voltando ao menu principal...");
                    return;
                }

                // Adiciona a tag na tarefa e depois a tarefa na tag correspondente.
                tarefas.get(tarefaParaInserirTag).setTags(novaTag);
                tags.get(novaTag).add(tarefas.get(tarefaParaInserirTag));
                System.out.println("Tag adicionada para tarefa com sucesso!");
                break;

            case "2": // Não adicionar tag em tarefa
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida! Voltando ao menu principal...");
                break;
        }
    }

    public void concluirTarefas() {

        tarefas.forEach((nome, tarefa) -> {
            System.out.println(nome + " - Concluido: " + tarefa.isConcluido());
        });
        System.out.println("Digite uma tarefa para alterar seu estado de conclusão: ");
        tarefas.get(scanner.nextLine()).alternarConclusao();
        System.out.println("Estado de conclusão alterado!");
    }

    /// COMEÇA PROGRAMA
    @Override
    public void run(String... args) throws Exception {
        //MockData.popularDadosTeste(tarefas, tags); // Mock data pra testes
        while(true){

            // Menu do usuário
            uiMenu();
            System.out.println("""
                    1 - Adicionar Tarefas
                    2 - Consultar Tarefas
                    3 - Adicionar Tags
                    4 - Sair do RoutiNATOR
                    """);

            switch(scanner.nextLine()){
                case "1":
                    uiMenu();
                    criaTarefa();
                    break;
                case "2":
                    uiMenu();
                    menuConsulta(consultarTarefa());
                    break;
                case "3":
                    adicionarTag();
                    break;
                case "4":
                    System.out.println(Color.VERMELHO + "Encerrando programa, obrigado por utilizar o RoutiNator" + Color.RESET);
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}