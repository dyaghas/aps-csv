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
                String[] parte = linha.split(",");
                //condicional que evita parse do nome da coluna 'id' para int
                if(!parte[0].equals("id")) {
                    int id = Integer.parseInt(parte[0]);
                    String nome = parte[1];
                    updateMaxId(id);
                    alunos.put(id, nome);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //exibe os alunos armazenados no hashmap
    public void listarAlunos() {
        StringBuilder str = new StringBuilder();
        System.out.println("id nome");
        for(HashMap.Entry<Integer, String> set : alunos.entrySet()) {
            //guarda os valores de cada instância no StringBuilder
            str.append(set.getKey()).append(" ").append(set.getValue()).append("\n");
        }
        System.out.println(str);
    }

    public void cadastrarAluno(@NotNull Scanner scan) throws IOException {
        System.out.print("Digite o nome do aluno: ");
        String nome = scan.nextLine();
        if(verificarNome(nome)) {
            FileWriter fr = new FileWriter(alunoCsv, true);
            BufferedWriter br = new BufferedWriter(fr);
            int newId = getNewId();
            br.newLine();
            br.write(newId + "," + nome);
            alunos.put(newId, nome);
            System.out.println(newId + " " + nome);
            br.close();
        } else {
            System.out.println("Nome deve conter entre 3 e 50 caracteres");
        }
    }

    public void updateMaxId(int id) {
        if(id > maxId) {
            maxId = id;
        }
    }

    public boolean verificarNome(String nome) {
        return nome.length() >= 3 && nome.length() <= 50;
    }

    //getters e setters

    public File getAlunoCsv() {
        return this.alunoCsv;
    }

    //procura o primeiro valor disponível para criar um id
    public int getNewId() {
        while(alunos.containsKey(novoId)) {
            novoId++;
        }
        return novoId++;
    }
}