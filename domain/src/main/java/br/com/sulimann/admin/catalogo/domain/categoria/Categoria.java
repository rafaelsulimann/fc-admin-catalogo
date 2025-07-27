package br.com.sulimann.admin.catalogo.domain.categoria;

import java.time.Instant;

import br.com.sulimann.admin.catalogo.domain.AgregateRoot;

public class Categoria extends AgregateRoot<CategoriaId>{

    private String nome;
    private String descricao;
    private boolean active;
    private Instant dataCriacao;
    private Instant dataUltimaAtualizacao;
    private Instant dataDelecao;

    private Categoria(
            final CategoriaId id,
            final String nome,
            final String descricao,
            final boolean active,
            final Instant dataCriacao,
            final Instant dataUltimaAtualizacao,
            final Instant dataDelecao
    ) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.active = active;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.dataDelecao = dataDelecao;
    }

    public static Categoria novaCategoria(final String nome, final String descricao, final boolean isActive) {
        var id = CategoriaId.unique();
        var now = Instant.now();
        return new Categoria(id, nome, descricao, isActive, now, now, null);
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
