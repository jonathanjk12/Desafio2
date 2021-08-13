package aplicativos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Motorista;

public class AppHeranca {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20;
        final int MAX_ERROS_CPF = 3;
        int opcao, qtdCadastrados = 0;
        Motorista[] motoristas = new Motorista[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar motorista");
            System.out.println("2 - Listar motoristas cadastrados");
            System.out.println("3 - Buscar um motorista pelo CPF");
            System.out.println("4 - Ampliar a capacidade de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); 

            if (opcao == 1) {
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos motoristas.");
                    voltarMenu(in);
                    continue;
                }

                Motorista mot = new Motorista();

                System.out.print("Nome: ");
                mot.setNome(in.nextLine());

                System.out.print("Habilitação: ");
                mot.setHabilitacao(in.nextLine());

                int numVezes = 0;
                do {
                    try {
                        System.out.print("CPF: ");
                        mot.setCpf(in.nextLine());
                    } catch (InputMismatchException ex) {
                        System.out.println(ex.getMessage() + " Tente novamente.");
                        numVezes += 1;
                    }
                } while (mot.getCpf() == null && numVezes < MAX_ERROS_CPF);

                if (mot.getCpf() == null) {
                    System.out.printf("Você errou o CPF %d vezes. O programa será encerrado.", numVezes);
                    return;
                }

                motoristas[qtdCadastrados] = mot;
                qtdCadastrados = qtdCadastrados + 1;

                System.out.println("\nMotorista cadastrado com sucesso.");
                voltarMenu(in);
            } else if (opcao == 2) {
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há motoristas cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("\nMOTORISTAS CADASTRADOS:");
                System.out.println("***********************");

                for (int i = 0; i < qtdCadastrados; i++) {
                    exibirMotorista(morotistas[i]);
                
                }

                voltarMenu(in);
            }else if (opcao == 3) {
                if (qtdCadastrados == 0){
                    System.out.println("\nNão há motoristas cadastrados para exibir. ");
                    voltarMenu(in);
                    continue;
                }

                System.out.print("\nCPFpara buscar: ");
                String cpf = in.nextLine();

                Motorista motBusca = null;
                for (int i = 0; motBusca == null && i < qtdCadastrados; i++){
                    if (cpf.equals(motoristas[i].getCpf())){
                        motBusca = motoristas[i];
                    }
                }

                if (motBusca ==null){
                    System.out.println("CPF não encontrado.");
                } else { 
                    System.out.println("CPF encontrado: ");
                    exibiMotorista(motBusca);
                }

                voltarMenu(in);

            } else if (opcao == 4) {
                System.out.println("Informe a nova capacidade de armazenamento>: ");
                int novoArm = in.nextInt();
                in.nextLine();

                if(novoArm<= MAX_ELEMENTOS){
                    System.out.println("O novo tamanho deve ser maior que o tamanho atual. ");
                    voltarMenu(in);
                    continue;
                }

                Motorista[] tmp = motoristas;
                motoristas = new Motorista[novoArm];
                for (int i = 0; i < qtdCadastrados; i++){
                    motoristas[i] = tmp[i];
                }

                MAX_ELEMENTOS = novoArm;

                System.out.println("Capacidade de armazenamento ampliada! ");
                voltarMenu(in);
            
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }

    private static void exibirMotorista(Motorista motorista) {
        System.out.printf("\nMotorista: %s\n", motorista.getNome());
        System.out.printf("CPF: %s\n", motorista.getCpf());
        System.out.printf("Habilitação: %s\n", motorista.getHabilitacao());
    }
}