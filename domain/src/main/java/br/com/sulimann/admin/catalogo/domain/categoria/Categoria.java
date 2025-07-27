package br.com.sulimann.admin.catalogo.domain.categoria;

import java.time.Instant;
import java.util.UUID;

public class Categoria {

    private String id;
    private String nome;
    private String descricao;
    private boolean active;
    private Instant dataCriacao;
    private Instant dataUltimaAtualizacao;
    private Instant dataDelecao;

    private Categoria(
            final String id,
            final String nome,
            final String descricao,
            final boolean active,
            final Instant dataCriacao,
            final Instant dataUltimaAtualizacao,
            final Instant dataDelecao
    ) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.active = active;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.dataDelecao = dataDelecao;
    }

    public static Categoria novaCategoria(final String nome, final String descricao, final boolean isActive) {
        var id = UUID.randomUUID().toString().toLowerCase();
        var now = Instant.now();
        return new Categoria(id, nome, descricao, isActive, now, now, null);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public Instant getDataDelecao() {
        return dataDelecao;
    }
}
