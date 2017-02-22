package app.model.domain;

import java.math.BigDecimal;

public class Produtos {

    private Integer cdproduto;
    private String nome;
    private double preco;
    private Integer quantidade;
    private String categoria;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
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
