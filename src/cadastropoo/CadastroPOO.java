package cadastropoo;

import cadastropoo.model.PessoaFisica;
import cadastropoo.model.PessoaFisicaRepo;
import cadastropoo.model.PessoaJuridica;
import cadastropoo.model.PessoaJuridicaRepo;
import java.util.Scanner;

public class CadastroPOO {

    private static PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            
            try {
                switch (opcao) {
                    case 1:
                        incluirPessoa();
                        break;
                    case 2:
                        alterarPessoa();
                        break;
                    case 3:
                        excluirPessoa();
                        break;
                    case 4:
                        buscarPeloId();
                        break;
                    case 5:
                        exibirTodos();
                        break;
                    case 6:
                        persistirDados();
                        break;
                    case 7:
                        recuperarDados();
                        break;
                    case 0:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            
            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===================================");
        System.out.println("1 - Incluir Pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Buscar pelo Id");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Persistir Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa");
        System.out.println("===================================");
        System.out.print("Escolha uma opção: ");
    }

    private static void incluirPessoa() {
        System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
        System.out.print("Escolha o tipo: ");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        System.out.println("Insira os dados...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        if (tipo.equals("F")) {
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            
            PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.inserir(pf);
            System.out.println("Pessoa Física cadastrada com sucesso!");
            
        } else if (tipo.equals("J")) {
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            
            PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.inserir(pj);
            System.out.println("Pessoa Jurídica cadastrada com sucesso!");
            
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void alterarPessoa() {
        System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
        System.out.print("Escolha o tipo: ");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        if (tipo.equals("F")) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                System.out.println("Insira os novos dados...");
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                
                PessoaFisica pfAlterada = new PessoaFisica(id, nome, cpf, idade);
                repoFisica.alterar(pfAlterada);
                System.out.println("Pessoa Física alterada com sucesso!");
            } else {
                System.out.println("Pessoa Física não encontrada!");
            }
            
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                System.out.println("Insira os novos dados...");
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                
                PessoaJuridica pjAlterada = new PessoaJuridica(id, nome, cnpj);
                repoJuridica.alterar(pjAlterada);
                System.out.println("Pessoa Jurídica alterada com sucesso!");
            } else {
                System.out.println("Pessoa Jurídica não encontrada!");
            }
            
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void excluirPessoa() {
        System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
        System.out.print("Escolha o tipo: ");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        if (tipo.equals("F")) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                repoFisica.excluir(id);
                System.out.println("Pessoa Física excluída com sucesso!");
            } else {
                System.out.println("Pessoa Física não encontrada!");
            }
            
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                repoJuridica.excluir(id);
                System.out.println("Pessoa Jurídica excluída com sucesso!");
            } else {
                System.out.println("Pessoa Jurídica não encontrada!");
            }
            
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void buscarPeloId() {
        System.out.println("F – Pessoa Fisica | J – Pessoa Juridica");
        System.out.print("Escolha o tipo: ");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        if (tipo.equals("F")) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa Física não encontrada!");
            }
            
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa Jurídica não encontrada!");
            }
            
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void exibirTodos() {
        System.out.println("\n--- Pessoas Físicas ---");
        for (PessoaFisica pf : repoFisica.obterTodos()) {
            pf.exibir();
            System.out.println();
        }
        
        System.out.println("\n--- Pessoas Jurídicas ---");
        for (PessoaJuridica pj : repoJuridica.obterTodos()) {
            pj.exibir();
            System.out.println();
        }
    }

    private static void persistirDados() throws Exception {
        repoFisica.persistir("pessoas_fisicas.dat");
        repoJuridica.persistir("pessoas_juridicas.dat");
        System.out.println("Dados persistidos com sucesso!");
    }

    private static void recuperarDados() throws Exception {
        repoFisica.recuperar("pessoas_fisicas.dat");
        repoJuridica.recuperar("pessoas_juridicas.dat");
        System.out.println("Dados recuperados com sucesso!");
    }
}
