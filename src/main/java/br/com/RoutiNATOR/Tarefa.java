package br.com.RoutiNATOR;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Tarefa {
/// OBJETO TA TAREFA
    private String nome;
    private String descricao;
    private boolean concluido;
    private int prioridade;
    private Instant dataDeCriacao;

    @Override
    public String toString() {
        String status = concluido
                ? Color.VERDE + "✅ CONCLUÍDA" + Color.RESET
                : Color.VERMELHO + "❌ PENDENTE" + Color.RESET;

        LocalDateTime dataFormatada = LocalDateTime.ofInstant(
                dataDeCriacao,
                ZoneId.systemDefault()
        );

        return """
                                    %sDETALHES DA TAREFA
                ##########################################################%s
                Nome: %s
                
                Descrição: 
                %s
                
                Status: %s
                Prioridade: %d
                
                Criada em: %s
                ##########################################################
                """.formatted(Color.AZUL, Color.RESET,nome, descricao, status, prioridade, dataFormatada);
    }

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }


    public Tarefa(String nome, String descricao, int prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.concluido = false;
        this.prioridade = prioridade;
        this.dataDeCriacao = Instant.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDateTime getLocalCreationTime() {

        return LocalDateTime.ofInstant(
                dataDeCriacao,
                ZoneId.systemDefault()
        );
    }
}
