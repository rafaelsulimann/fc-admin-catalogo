package br.com.sulimann.admin.catalogo.domain.categoria;

import br.com.sulimann.admin.catalogo.domain.AgregateRoot;

import java.time.Instant;
import java.util.Objects;

import static br.com.sulimann.admin.catalogo.domain.validation.AssertUtils.isTrue;
import static br.com.sulimann.admin.catalogo.domain.validation.AssertUtils.notNull;

public class Categoria extends AgregateRoot<CategoriaId>{

    private String nome;
    private String descricao;
    private Boolean active;
    private Instant dataCriacao;
    private Instant dataUltimaAtualizacao;
    private Instant dataDelecao;

    private Categoria(
            final CategoriaId id,
            final String nome,
            final String descricao,
            final Boolean active,
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
        this.validate();
    }

    public static Categoria novaCategoria(final String nome, final String descricao, final Boolean isActive) {
        var id = CategoriaId.unique();
        var now = Instant.now();
        var dataDelecao = isActive != null && isActive ? null : now;
        return new Categoria(id, nome, descricao, isActive, now, now, dataDelecao);
    }

    public void validate(){
        notNull(this.nome, "'nome' nao pode ser nulo");
        isTrue(this.nome.trim().length() >= 3 && this.nome.trim().length() <= 255, "'nome' deve ter entre 3 e 255 caracteres");
        notNull(this.active, "'active' nao pode ser nulo");
        notNull(this.dataCriacao, "'dataCriacao' nao pode ser nulo");
        notNull(this.dataUltimaAtualizacao, "'dataUltimaAtualizacao' nao pode ser nulo");

        if(isActive()){
            isTrue(this.active && Objects.isNull(dataDelecao), "'dataDelecao' precisa ser nulo caso o usuario estiver ativo");
        }else {
            isTrue(Objects.nonNull(this.dataDelecao) && !this.active, "Usuario nao pode estar ativo se 'dataDelecao' nao for nulo");
        }
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
