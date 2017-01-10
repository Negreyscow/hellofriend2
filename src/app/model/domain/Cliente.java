package app.model.domain;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int cdCliente;
    private String nome;
    private String endereco;
    private String telefone;
    private String usuario;
    private String senha;



    private String nivelAcesso;

    public Cliente(){
    }
    
    public Cliente(int cdCliente, String nome, String endereco,String usuario,String senha, String nivelAcesso) {
        this.cdCliente = cdCliente;
        this.nome = nome;
        this.endereco = endereco;
        this.usuario = usuario;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario(){return usuario;}

    public void setUsuario(String usuario){this.usuario = usuario;}

    public String getSenha(){return senha;}

    public void setSenha(String senha){this.senha = senha;}

    public String getNivelAcesso() {return nivelAcesso;}

    public void setNivelAcesso(String nivelAcesso) {this.nivelAcesso = nivelAcesso;
    }


    @Override
    public String toString() {
        return this.nome;
    }
    
}
