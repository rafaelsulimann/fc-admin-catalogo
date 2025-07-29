package br.com.sulimann.admin.catalogo.domain.categoria

import br.com.sulimann.admin.catalogo.domain.exceptions.DomainException
import spock.lang.Specification
import spock.lang.Unroll

import java.time.Instant

class CategoriaTest extends Specification {

    @Unroll
    def "Deve instanciar uma categoria com parametros validos: #expectedNome,  #expectedDescricao , #expectedActive"(){
        given: "parametros validos de uma categoria"

        when: "crio nova categoria"
        def categoria = Categoria.novaCategoria(expectedNome, expectedDescricao, expectedActive)

        then: "categoria deve ser criada"
        categoria != null

        then: "categoria deve ser criada corretamente"
        verifyAll (categoria){
            it != null
            id != null
            nome == expectedNome
            descricao == expectedDescricao
            active == expectedActive
            dataCriacao != null
            dataUltimaAtualizacao != null
            expectedActive ? dataDelecao == null : dataDelecao != null
        }

        and: "datas devem ser iguais"
        verifyAll (categoria){
            dataCriacao == dataUltimaAtualizacao
            if(!expectedActive){
                dataDelecao == dataCriacao
            }
        }

        where:
        expectedNome      | expectedDescricao    | expectedActive
        "Terror"          | "Melhor categoria"   | true
        "ABC"             | null                 | false
        "  ABC  "         | ""                   | true
        'A' * 255         | "Descrição longa"    | false

    }

    def "Deve lançar DomainExpection quando nome for nulo "(){
        given: "parametros invalidos de uma categoria com nome nulo"
        def expectedNome = null
        def expectedDescricao = "Melhor categoria"
        def expectedActive = true

        when: "crio nova categoria"
        def categoria = Categoria.novaCategoria(expectedNome, expectedDescricao, expectedActive)

        then: "deve lançar DomainException"
        def domainException = thrown(DomainException)

        and: "possuir a mensagem correta"
        domainException.message.contentEquals("'nome' nao pode ser nulo")
    }

    @Unroll
    def "Deve lançar DomainExpection quando nome da categoria for menor que 3 ou maior que 255 caracteres: #expectedNome "(){
        given: "parametros invalidos de uma categoria com nome menor que 3 ou maior que 255 caracteres"
        def expectedDescricao = "Melhor categoria"
        def expectedActive = true

        when: "crio nova categoria"
        def categoria = Categoria.novaCategoria(expectedNome, expectedDescricao, expectedActive)

        then: "deve lançar DomainException"
        def domainException = thrown(DomainException)

        and: "possuir a mensagem correta"
        domainException.message.contentEquals("'nome' deve ter entre 3 e 255 caracteres")

        where:
        expectedNome << ["AB", "A", "   ", " AB ", "", 'A' * 256]
    }

    def "Deve lançar DomainExpection quando active for nulo "(){
        given: "parametros invalidos de uma categoria com active nulo"
        def expectedNome = "Terror"
        def expectedDescricao = "Melhor categoria"
        def expectedActive = null

        when: "crio nova categoria"
        def categoria = Categoria.novaCategoria(expectedNome, expectedDescricao, expectedActive)

        then: "deve lançar DomainException"
        def domainException = thrown(DomainException)

        and: "possuir a mensagem correta"
        domainException.message.contentEquals("'active' nao pode ser nulo")
    }

    def "Deve lançar DomainExpection quando active for true e data Deleçao nao for nulo"(){
        given: "parametros validos de uma categoria com active true"
        def expectedNome = "Terror"
        def expectedDescricao = "Melhor categoria"
        def expectedActive = true

        when: "crio nova categoria"
        def categoria = Categoria.novaCategoria(expectedNome, expectedDescricao, expectedActive)

        and: "forço a data delecao a nao ser nulo"
        def dataDelecaoField = Categoria.getDeclaredField("dataDelecao")
        dataDelecaoField.setAccessible(true)
        dataDelecaoField.set(categoria, Instant.now())

        and: "valido a categoria novamente"
        categoria.validate()

        then: "deve lançar DomainException"
        def domainException = thrown(DomainException)

        and: "possuir a mensagem correta"
        domainException.message.contentEquals("'dataDelecao' precisa ser nulo caso o usuario estiver ativo")
    }

    def "Deve lançar DomainExpection quando active for false e data Deleçao for nulo"(){
        given: "parametros validos de uma categoria com active true"
        def expectedNome = "Terror"
        def expectedDescricao = "Melhor categoria"
        def expectedActive = false

        when: "crio nova categoria"
        def categoria = Categoria.novaCategoria(expectedNome, expectedDescricao, expectedActive)

        and: "forço a data delecao a ser nulo"
        def dataDelecaoField = Categoria.getDeclaredField("dataDelecao")
        dataDelecaoField.setAccessible(true)
        dataDelecaoField.set(categoria, null)

        and: "valido a categoria novamente"
        categoria.validate()

        then: "deve lançar DomainException"
        def domainException = thrown(DomainException)

        and: "possuir a mensagem correta"
        domainException.message.contentEquals("Usuario nao pode estar ativo se 'dataDelecao' nao for nulo")
    }

}
