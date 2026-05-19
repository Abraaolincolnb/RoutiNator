package br.com.RoutiNATOR;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

@SpringBootApplication
public class RoutiNatorApplication implements CommandLineRunner {

	public static void main(String[] args)   {SpringApplication.run(RoutiNatorApplication.class, args);
	}


    public void criaTarefa() {

    }


    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Tarefa tarefa = new Tarefa("asd", "asda",true, 1);

        while(true){
            System.out.println("Bem vindo ao RoutiNATOR"); //skynet*
            System.out.println("Seu organizador de tarefas super dahora");
            System.out.println("""
                    1 - Adicionar Tarefas
                    2 - Consultar Tarefas
                    3 - Sair do RoutiNATOR
                    """);

            int opcao = scanner.nextInt();

            switch(opcao){
                case 1:

                case 2:

                case 3:

                default:
                    System.out.println("Opção Inválida");
            }

        }

    }
}

