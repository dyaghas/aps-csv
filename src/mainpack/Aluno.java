package mainpack;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Aluno {
    private int maxId;
    File alunoCsv = new File("./alunos.csv");
    Scanner scan = new Scanner(System.in);

    //hashmap que guarda as linhas do arquivo aluno.csv
    HashMap<Integer, String> alunos = new HashMap<>();

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

    public void cadastrarAluno() throws IOException {
        System.out.print("Digite o nome do aluno: ");
        String nome = scan.nextLine();
        if(verificaNome(nome)) {
            FileWriter fr = new FileWriter(alunoCsv, true);
            BufferedWriter br = new BufferedWriter(fr);
            int newId = getNewId();
            br.newLine();
            br.write(newId + "," + nome);
            alunos.put(newId, nome);
            System.out.println(newId + " " + nome);
            br.close();
            //leitura para impedir que dados sejam sobrescritos ao serem alterados enquanto o código está em execução
            lerCsv();
        } else {
            System.out.println("Nome deve conter entre 3 e 50 caracteres");
        }
    }

    //procura o primeiro valor disponível para criar um id
    public int getNewId() {
        Set<Integer> idsExistentes = alunos.keySet();
        int newId = 1;
        while(idsExistentes.contains(newId)) {
            newId++;
        }
        updateMaxId(newId);
        return newId;
    }

    public void updateMaxId(int id) {
        if(id > maxId) {
            maxId = id;
        }
    }

    public boolean verificaNome(String nome) {
        if(nome.length() >= 3 && nome.length() <= 50) {
            return true;
        } else {
            return false;
        }
    }
}
