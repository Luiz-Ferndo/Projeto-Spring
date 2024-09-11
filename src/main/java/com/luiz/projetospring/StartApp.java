package com.luiz.projetospring;

import com.luiz.projetospring.Controller.UserController;
import com.luiz.projetospring.model.User;
import com.luiz.projetospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartApp implements CommandLineRunner {
    private Integer op = 0;
    Scanner read = new Scanner(System.in);
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserController controller;

    public void run(String... args) throws Exception {
        while (op != 4) {
            System.out.println("======MENU======");
            System.out.println("1. Exibir todos os usuários.");
            System.out.println("2. Achar o usuário por ID.");
            System.out.println("3. Cadastrar um Usuário novo.");
            System.out.println("4. Sair.");
            System.out.print("Selecione uma opção: ");
            op = read.nextInt();
            read.nextLine();
            switch (op) {
                case 1:
                    System.out.println(controller.listUsers());;
                    break;
                case 2:
                    System.out.print("Informe o ID que deseja procurar: ");
                    Integer id = read.nextInt();
                    read.nextLine();
                    System.out.println(controller.findUserByID(id));;
                    break;
                case 3:
                    System.out.print("Informe o nome de usuário: ");
                    String username = read.nextLine();
                    System.out.println("Informe a senha: ");
                    String password = read.nextLine();
                    System.out.println("Informe o nome completo: ");
                    String name = read.nextLine();

                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setUsername(username);
                    newUser.setPassword(password);

                    User createdUser = controller.createUser(newUser).getBody();
                    System.out.println("Usuário Criado: " + createdUser);
                    break;
                case 4:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção Inválida. Tente Novamente!");
                    break;
            }
        }
        for (User u : repository.findAll()) {
            System.out.println(u);
        }
            read.close();
    }
}
