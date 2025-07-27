package br.com.sulimann.admin.catalogo.domain.categoria

import spock.lang.Specification

class CategoriaTest extends Specification {

    def """Dado válidos parâmetros, quando chamamos New Categoria, então deve ser instanciado uma categoria"""(){
        given:
        def nome = "Terror"
        def descricao = "Melhor filme de terror"
        def isActive = true

        when:
        def categoria = Categoria.novaCategoria(nome, descricao, isActive)

        then:
        categoria != null
        categoria.id != null
        categoria.nome == nome
        categoria.descricao == descricao
        categoria.active == isActive
        categoria.dataCriacao != null
        categoria.dataUltimaAtualizacao != null
        categoria.dataDelecao == null
    }

}
