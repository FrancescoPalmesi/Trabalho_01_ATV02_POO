package controller;

import dao.ClienteDAO;
import dao.ContaDAO;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.Scanner;

public class SistemaGestaoBancariaController {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ContaDAO contaDAO = new ContaDAO();
    private Scanner scanner = new Scanner(System.in);

    public void menuPrincipal() {
        while (true) {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1-Gerenciar CLIENTES");
            System.out.println("2-Gerenciar CONTAS");
            System.out.println("3-SAIR");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuContas();
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void menuClientes() {
        while (true) {
            System.out.println("MENU CLIENTES");
            System.out.println("1-Cadastrar CLIENTE");
            System.out.println("2-Consultar CLIENTE");
            System.out.println("3-Remover CLIENTE");
            System.out.println("4-Atualizar CLIENTE");
            System.out.println("5-Voltar ao MENU INICIAL");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> consultarCliente();
                case 3 -> removerCliente();
                case 4 -> atualizarCliente();
                case 5 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void menuContas() {
        while (true) {
            System.out.println("MENU CONTAS");
            System.out.println("1-Criar CONTA para um CLIENTE");
            System.out.println("2-Sacar dinheiro de uma CONTA de um CLIENTE");
            System.out.println("3-Depositar dinheiro para uma CONTA de um CLIENTE");
            System.out.println("4-Verificar saldo de uma CONTA de um CLIENTE");
            System.out.println("5-Transferir dinheiro de uma CONTA de um CLIENTE para outro CLIENTE");
            System.out.println("6-Voltar ao MENU INICIAL");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> sacar();
                case 3 -> depositar();
                case 4 -> verificarSaldo();
                case 5 -> transferir();
                case 6 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // Métodos para gerenciar clientes
    private void cadastrarCliente() {
        System.out.println("Nome do cliente:");
        String nome = scanner.nextLine();
        System.out.println("CPF do cliente:");
        String cpf = scanner.nextLine();
        Cliente cliente = new Cliente(nome, cpf);
        clienteDAO.adicionarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void consultarCliente() {
        System.out.println("CPF do cliente para consulta:");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteDAO.buscarCliente(cpf);
        if (cliente != null) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private void removerCliente() {
        System.out.println("CPF do cliente para remover:");
        String cpf = scanner.nextLine();
        clienteDAO.removerCliente(cpf);
        System.out.println("Cliente removido com sucesso!");
    }

    private void atualizarCliente() {
        System.out.println("CPF do cliente para atualizar:");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteDAO.buscarCliente(cpf);
        if (cliente != null) {
            System.out.println("Novo nome:");
            cliente.setNome(scanner.nextLine());
            System.out.println("Novo CPF:");
            cliente.setCpf(scanner.nextLine());
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    // Métodos para gerenciar contas
    private void criarConta() {
        System.out.println("CPF do cliente para criar conta:");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteDAO.buscarCliente(cpf);
        if (cliente != null) {
            System.out.println("Tipo de conta (1 - Corrente, 2 - Poupança):");
            int tipoConta = scanner.nextInt();
            scanner.nextLine();
            Conta conta;
            if (tipoConta == 1) {
                conta = new ContaCorrente(cliente);
            } else {
                conta = new ContaPoupanca(cliente);
            }
            contaDAO.adicionarConta(conta);
            System.out.println("Conta criada com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private void sacar() {
        System.out.println("CPF do cliente para saque:");
        String cpf = scanner.nextLine();
        Conta conta = contaDAO.buscarConta(cpf);
        if (conta != null) {
            System.out.println("Valor para saque:");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            if (conta.sacar(valor)) {
                System.out.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente!");
            }
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    private void depositar() {
        System.out.println("CPF do cliente para depósito:");
        String cpf = scanner.nextLine();
        Conta conta = contaDAO.buscarConta(cpf);
        if (conta != null) {
            System.out.println("Valor para depósito:");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    private void verificarSaldo() {
        System.out.println("CPF do cliente para verificar saldo:");
        String cpf = scanner.nextLine();
        Conta conta = contaDAO.buscarConta(cpf);
        if (conta != null) {
            System.out.println("Saldo atual: " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    private void transferir() {
        System.out.println("CPF do cliente de origem:");
        String cpfOrigem = scanner.nextLine();
        Conta contaOrigem = contaDAO.buscarConta(cpfOrigem);
        if (contaOrigem != null) {
            System.out.println("CPF do cliente de destino:");
            String cpfDestino = scanner.nextLine();
            Conta contaDestino = contaDAO.buscarConta(cpfDestino);
            if (contaDestino != null) {
                System.out.println("Valor para transferir:");
                double valor = scanner.nextDouble();
                scanner.nextLine();
                contaOrigem.transferir(contaDestino, valor);
                System.out.println("Transferência realizada com sucesso!");
            } else {
                System.out.println("Conta de destino não encontrada!");
            }
        } else {
            System.out.println("Conta de origem não encontrada!");
        }
    }
}