package app.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Produtos implements Serializable {

    private int codProduto;
    private String marca;
    private String tipo;
    private String tamanho;
    private String cor;


    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

   // @Override
   // public String toString() {
    //    return this.nome;
    //}
    
}
