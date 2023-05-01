package mainpack;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Aluno implements CsvInterface {
    private int maxId;
    private int novoId;
    private final File alunoCsv = new File("./alunos.csv");

    //hashmap que guarda as linhas do arquivo aluno.csv
    HashMap<Integer, String> alunos = new HashMap<>();

    public Aluno() {
        lerCsv();
    }

    //instancia o arquivo csv no hashmap
    public void lerCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(alunoCsv))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                processaLinhaCsv(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Realiza as operações de leitura em cada linha do csv
    private void processaLinhaCsv(String linha) {
        String[] parte = linha.split(",");
        //condicional que evita parse do nome da coluna 'id' para int
        if(!parte[0].equals("id")) {
            int id = Integer.parseInt(parte[0]);
            String nome = parte[1];
            updateMaxId(id);
            alunos.put(id, nome);
        }
    }

    //exibe os alunos armazenados no hashmap
    public void listarCsv() {
        String alunosStr = gerarStringAlunos();
        System.out.println(alunosStr);
    }

    private String gerarStringAlunos() {
        StringBuilder str = new StringBuilder();
        System.out.println("id nome");
        //Transforma o Hashmap em um hashSet para possibilitar a iteração
        for(HashMap.Entry<Integer, String> set : alunos.entrySet()) {
            //guarda os valores de cada instância no StringBuilder
            str.append(set.getKey()).append(" ").append(set.getValue()).append("\n");
        }
        return str.toString();
    }

    // Cadastra o aluno em "alunos.csv"
    public void cadastrarDadoCsv(Scanner scan) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scan.nextLine();
        if(verificarNome(nome)) {
            try {
                FileWriter fr = new FileWriter(alunoCsv, true);
                BufferedWriter br = new BufferedWriter(fr);
                int newId = getNewId();
                br.newLine();
                br.write(newId + "," + nome);
                alunos.put(newId, nome);
                System.out.println(newId + " " + nome);
                br.close();
            } catch(IOException e) {
                System.out.println("\nErro ao cadastrar aluno: " + e.getMessage());
            }
        } else {
            System.out.println("\nNome deve conter entre 3 e 50 caracteres\n");
        }
    }

    //método responsável por escolher qual será o id do próximo aluno cadastrado
    public void updateMaxId(int id) {
        if(id > maxId) {
            maxId = id;
        }
    }

    public boolean verificarNome(String nome) {
        return nome.length() >= 3 && nome.length() <= 50;
    }

    public HashMap getAlunosHashMap() {
        return this.alunos;
    }

    //procura o primeiro valor disponível para criar um id
    public int getNewId() {
        while(alunos.containsKey(novoId)) {
            novoId++;
        }
        return novoId++;
    }
}