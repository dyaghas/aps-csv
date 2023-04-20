package mainpack;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws IOException {
        //acessa o arquivo csv com os dados dos alunos
        File alunoCsv = new File("./alunos.csv");
        Aluno alunos = new Aluno();
        //instancia o arquivo csv em um hashmap
        alunos.lerCsv();
        while(true) {
            System.out.println(
                    "Digite o número correspondente a ação que deseja realizar\n" +
                            "0 - finalizar programa\n" +
                            "1 - Listar alunos\n" +
                            "2 - Cadastrar aluno"
            );
            try {
                Scanner scan = new Scanner(System.in);
                int escolha = scan.nextInt();
                scan.nextLine();
                switch (escolha) {
                    case 0:
                        exit(0);
                        break;
                    case 1:
                        alunos.exibirCsv();
                        break;
                    case 2:
                        System.out.print("Digite o nome do aluno: ");
                        String nome = scan.nextLine();
                        alunos.cadastrarAluno(nome);
                        break;
                    default:
                        System.out.println("\nentrada incorreta\n");
                }
            } catch(InputMismatchException e) {
                System.out.println("\nErro: digite um número válido\n");
            }
        }
    }
}
