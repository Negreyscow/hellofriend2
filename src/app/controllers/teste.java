package app.controllers;

import app.model.domain.Funcionario;

/**
 * Created by Leonardo on 21/02/2017.
 */
public class teste {


    public static void  mainTraizBolacha(){

        CrudFuncionarioControllerNew func = new CrudFuncionarioControllerNew();
        Funcionario alterarFunc = func.getUltimoHomem();


    }

    public Funcionario traizaBolacha(Funcionario func){
        return func;
    }






}
