package mainpack;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Aluno alunos = new Aluno();
        Curso cursos = new Curso();
        RendimentoGrad rendimento = new RendimentoGrad();
        RendimentoPosGrad rendimentoPosGrad = new RendimentoPosGrad();

        while(true) {
            System.out.println(
                    "Digite o número correspondente a ação que deseja realizar\n" +
                            "0 - Finalizar programa\n" +
                            "1 - Listar alunos\n" +
                            "2 - Cadastrar aluno\n" +
                            "3 - Listar cursos\n" +
                            "4 - Listar cursos por ano\n" +
                            "5 - Cadastrar curso\n" +
                            "6 - Deletar curso\n" +
                            "7 - Cadastrar rendimento\n"
            );
            try {
                int escolha = scan.nextInt();
                scan.nextLine();
                switch (escolha) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        alunos.listarCsv();
                        break;
                    case 2:
                        alunos.cadastrarDadoCsv(scan);
                        break;
                    case 3:
                        cursos.listarCsv();
                        break;
                    case 4:
                        cursos.exibirCursoAno(scan);
                        break;
                    case 5:
                        cursos.cadastrarDadoCsv(scan);
                        break;
                    case 6:
                        cursos.deletarCurso(scan);
                        break;
                    case 7:
                        System.out.println("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
                        int nivelCurso = scan.nextInt();
                        scan.nextLine();
                        if(nivelCurso == 1) {
                            rendimento.cadastrarRendimento(scan, alunos);
                        } else if(nivelCurso == 2) {
                            rendimentoPosGrad.cadastrarRendimento(scan, alunos);
                        } else {
                            System.out.println("Valor inválido\n");
                        }
                        break;
                    default:
                        System.out.println("\nEntrada incorreta\n");
                }
            //verifica se a entrada é um valor numérico
            } catch(InputMismatchException e) {
                System.out.println("\nErro: digite um número válido\n");
            }
        }
    }
}