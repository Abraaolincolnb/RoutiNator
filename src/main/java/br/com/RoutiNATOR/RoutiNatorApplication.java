package br.com.RoutiNATOR;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class RoutiNatorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RoutiNatorApplication.class);  /// pesquisar pq tem que colocar args aqui, e colocar args quando entender
    }
    /// INDEXAÇÃO DOS OBJETOS
    Scanner scanner = new Scanner(System.in); //o scanner utilizado em tudo
    Map<String, Tarefa> tarefas = new HashMap<>(); // O HashMap que guarda as tarefas do usuario


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

        // PEDE DADOS AO USUÁRIO PARA CRIAÇÃO DE TAREFA
        System.out.println(Color.VERDE_BRIGHT + "CRIAÇÃO DE TAREFAS" + Color.RESET);
        System.out.println("Digite o nome da tarefa: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite a prioridade da tarefa sendo 0 o menos importante (apenas numeros inteiros): ");
        int prioridade = 0;
        prioridade = Integer.parseInt(scanner.nextLine());

        Tarefa tarefa = new Tarefa(nome, descricao, prioridade);

        // ADICIONA TAREFA NO HASHMAP TAREFAS
        tarefas.put(nome, tarefa);
    }


    /// BUSCA TAREFA
    public String consultarTarefa(){
        tarefas.forEach((nome, tarefa) -> {
            String status = tarefa.isConcluido()
                    ? Color.VERDE + "✅ CONCLUÍDA" + Color.RESET
                    : Color.VERMELHO + "❌ PENDENTE" + Color.RESET;
            System.out.println("""
                    %s 
                    Status: %s
                    -----------------------------
                    """.formatted(nome, status));
        });

        System.out.println("Digite a tarefa desejada");
        String tarefaInput = scanner.nextLine();
        Tarefa tarefaBuscada =  tarefas.get(tarefaInput);
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
            case "1":

                break;
            case "2":
                System.out.println("Certeza? Digite Y para confirmar, qualuer outra tecla para cancelar");
                if (scanner.nextLine() .equals("Y")) {
                    System.out.println("tarefa removida com sucesso!");
                    tarefas.remove(tarefaInput);
                } else {
                    System.out.println("Tarefa não foi deletada");
                }
                break;
            case "3":

                break;
            default:
                break;

        }
    }

    /// COMEÇA PROGRAMA
    @Override
    public void run(String... args) throws Exception {
        while(true){

            // MENU DO USUÁRIO
            uiMenu();
            System.out.println("""
                    1 - Adicionar Tarefas
                    2 - Consultar Tarefas
                    3 - Sair do RoutiNATOR
                    """);

            int opcao = Integer.parseInt(scanner.nextLine());



            switch(opcao){
                case 1:
                    uiMenu();
                    criaTarefa();
                    break;
                case 2:
                    uiMenu();
                    menuConsulta(consultarTarefa());
                    break;
                case 3:
                    System.out.println(Color.VERMELHO + "Encerrando programa, obrigado por utilizar o RoutiNator" + Color.RESET);
                    return;
                default:
                    System.out.println("Opção Inválida");
            }

        }

    }
}

