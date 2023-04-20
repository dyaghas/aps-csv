package mainpack;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.exit;

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
                            "4 - Cadastrar curso\n"
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
                        System.out.print("Digite o nome do curso: ");
                        String nomeCurso = scan.nextLine();
                        System.out.print("Digite o nível do curso: ");
                        String nivelCurso = scan.nextLine();
                        System.out.print("Digite o ano do curso: ");
                        int anoCurso = scan.nextInt();
                        scan.nextLine();
                        cursos.cadastrarCurso(nomeCurso, nivelCurso, anoCurso);
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