package app.model.domain;

import java.math.BigDecimal;

public class Produtos {

    private Integer cdproduto;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
    private String categoria;



    public Integer getCdproduto() {
        return cdproduto;
    }

    public void setCdproduto(Integer cdproduto) {
        this.cdproduto = cdproduto;
    }

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
