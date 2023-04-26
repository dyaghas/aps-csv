package mainpack;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Aluno alunos = new Aluno();
        Curso cursos = new Curso();
        while(true) {
            System.out.println(
                    "Digite o número correspondente a ação que deseja realizar\n" +
                            "0 - finalizar programa\n" +
                            "1 - Listar alunos\n" +
                            "2 - Cadastrar aluno\n" +
                            "3 - Listar cursos\n" +
                            "4 - Cadastrar curso\n" +
                            "5 - Deletar curso\n"

            );
            try {
                int escolha = scan.nextInt();
                scan.nextLine();
                switch (escolha) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        alunos.listarAlunos();
                        break;
                    case 2:
                        alunos.cadastrarAluno(scan);
                        break;
                    case 3:
                        cursos.exibirCsv();
                        break;
                    case 4:
                        cursos.cadastrarCurso(scan);
                        break;
                    case 5:
                        cursos.deletarCurso(scan);
                        break;
                    case 6:
                        System.out.println("Não implementado ainda");
                        break;
                    default:
                        System.out.println("\nEntrada incorreta\n");
                }
            } catch(InputMismatchException e) {
                System.out.println("\nErro: digite um número válido\n");
            }
        }
    }
}