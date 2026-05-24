package br.com.RoutiNATOR;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Tarefa {
/// OBJETO TA TAREFA
    private String nome;
    private String descricao;
    private boolean concluido;
    private int prioridade;
    private Instant dataDeCriacao;
    private List<String> tags = new ArrayList<>();

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
                
                Tags; %s
                ##########################################################
                """.formatted(Color.AZUL, Color.RESET,nome, descricao, status, prioridade, dataFormatada, tags);
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(String tag) {
        tags.add(tag);
    }

    public void removeTags(String tag) {
        tags.remove(tag);
    }

    public void alternarConclusao() {
        this.concluido = !concluido;
    }
}
