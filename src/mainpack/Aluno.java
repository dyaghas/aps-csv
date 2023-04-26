package mainpack;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Aluno {
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
    public void listarAlunos() {
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
    public void cadastrarAluno(@NotNull Scanner scan) {
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
                System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
            }
        } else {
            System.out.println("Nome deve conter entre 3 e 50 caracteres");
        }
    }
    //procura o primeiro valor disponível para criar um id
    public int getNewId() {
        while(alunos.containsKey(novoId)) {
            novoId++;
        }
        return novoId++;
    }
    public void updateMaxId(int id) {
        if(id > maxId) {
            maxId = id;
        }
    }
    //verifica o comprimento do nome inserido pelo usuário
    public boolean verificarNome(String nome) {
        return nome.length() >= 3 && nome.length() <= 50;
    }
}
