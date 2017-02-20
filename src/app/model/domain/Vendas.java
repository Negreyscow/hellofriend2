package app.model.domain;

import java.math.BigDecimal;

/**
 * Created by Leonardo on 19/02/2017.
 */
public class Vendas {

    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
