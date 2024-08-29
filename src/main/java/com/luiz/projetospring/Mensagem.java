package com.luiz.projetospring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;




@Component
public class Mensagem implements CommandLineRunner {
    @Autowired
    private Remetente remetente;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Mensagem enviada por " + remetente.getNome()
                +"\nEmail: " + remetente.getEmail()
                + "\nTelefone: " + remetente.getTelefones());
        System.out.println("Cadastro aprovado com sucesso!");
    }
}
