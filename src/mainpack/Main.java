package mainpack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Aluno alunos = new Aluno();
        Curso cursos = new Curso();
        RendimentoGrad rendimentoGrad = new RendimentoGrad();
        RendimentoPosGrad rendimentoPosGrad = new RendimentoPosGrad();

        while(true) {
            System.out.print(
                    "\n0 - Finalizar programa\n" +
                    "1 - Listar alunos\n" +
                    "2 - Cadastrar aluno\n" +
                    "3 - Listar cursos\n" +
                    "4 - Listar cursos por ano\n" +
                    "5 - Cadastrar curso\n" +
                    "6 - Deletar curso\n" +
                    "7 - Cadastrar rendimento\n" +
                    "8 - Exibir status dos alunos em um curso\n" +
                    "9 - Exibir a média geral de um curso\n" +
                    "10 - Exibe todos os cursos que o aluno está e seus dados\n" +
                    "11 - Exibir a média geral de um aluno, baseando-se em todas as médias dele\n" +
                    "\nDigite o número correspondente a ação que deseja realizar: "
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
                        System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
                        int nivelCurso = scan.nextInt();
                        scan.nextLine();
                        if(nivelCurso == 1) {
                            rendimentoGrad.cadastrarRendimento(scan, alunos, "1");
                        } else if(nivelCurso == 2) {
                            rendimentoPosGrad.cadastrarRendimento(scan, alunos, "2");
                        } else {
                            System.out.println("Valor inválido\n");
                        }
                        break;
                    case 8:
                        // Usa o método exibirRendimento() da classe RendimentoGrad ou RendimentoPosGrad
                        System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
                        nivelCurso = scan.nextInt();
                        scan.nextLine();
                        if(nivelCurso == 1) {
                            rendimentoGrad.exibirRendimento(scan, "1");
                        } else if(nivelCurso == 2) {
                            rendimentoPosGrad.exibirRendimento(scan, "2");
                        } else {
                            System.out.println("Valor inválido\n");
                        }
                        break;
                    case 9:
                        System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
                        nivelCurso = scan.nextInt();
                        scan.nextLine();
                        if(nivelCurso == 1) {
                            rendimentoGrad.exibirMediaGeral(scan, "1");
                        } else if(nivelCurso == 2) {
                            rendimentoPosGrad.exibirMediaGeral(scan, "2");
                        } else {
                            System.out.println("Valor inválido\n");
                        }
                        break;
                    case 10:
                        rendimentoGrad.exibirRendimentoAluno(scan);
                        break;
                    case 11:
                        rendimentoGrad.exibirMediaGeralAluno(scan);
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