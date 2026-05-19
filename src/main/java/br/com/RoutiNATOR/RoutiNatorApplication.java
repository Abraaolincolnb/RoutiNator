package br.com.RoutiNATOR;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class RoutiNatorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RoutiNatorApplication.class);
    }
    /// INDEXAÇÃO DOS OBJETOS
    Scanner scanner = new Scanner(System.in); //o scanner utilizado em tudo
    Map<String, Tarefa> tarefas = new HashMap<>(); // O HashMap que guarda as tarefas do usuario


    /// CRIA TAREFA
    public void criaTarefa() {

        // PEDE DADOS AO USUÁRIO PARA CRIAÇÃO DE TAREFA
        System.out.println("Digite o nome do tarefa: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite a prioridade da tarefa sendo 0 o menos importante (apenas numeros inteiros): ");
        int prioridade = Integer.parseInt(scanner.nextLine());

        Tarefa tarefa = new Tarefa(nome, descricao, prioridade);

        // ADICIONA TAREFA NO HASHMAP TAREFAS
        tarefas.put(nome, tarefa);
    }


    /// BUSCA TAREFA
    public String consultarTarefa(){
        System.out.println(tarefas.keySet());
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
            System.out.println("Bem vindo ao RoutiNATOR");
            System.out.println("Seu organizador de tarefas super dahora");
            System.out.println("""
                    1 - Adicionar Tarefas
                    2 - Consultar Tarefas
                    3 - Sair do RoutiNATOR
                    """);

            int opcao = Integer.parseInt(scanner.nextLine());


            switch(opcao){
                case 1:
                    criaTarefa();
                    break;
                case 2:
                    menuConsulta(consultarTarefa());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção Inválida");
            }

        }

    }
}

