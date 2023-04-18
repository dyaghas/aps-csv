package mainpack;

import java.io.*;
import java.util.HashMap;

public class Aluno {
    //id sempre deve possuir um valor único para a identificação do aluno!
    private String id;
    private String nome;
    private int maxId;
    File alunoCsv = new File("./alunos.csv");

    HashMap<Integer, String> alunos = new HashMap<>();

    //instancia o arquivo csv em um hashmap
    public void lerCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(alunoCsv))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parte = linha.split(",");
                //condicional que pula a primeira linha do arquivo csv para
                //evitar parse do nome da coluna para int
                if(!parte[0].equals("id")) {
                    int id = Integer.parseInt(parte[0]);
                    String nome = parte[1];
                    alunos.put(id, nome);
                    maxId = id;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //exibe as informações do arquivo csv
    public void exibirCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(alunoCsv))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parte = linha.split(",");
                //condicional que pula a primeira linha do arquivo csv para
                //evitar parse do nome da coluna para int
                if(!parte[0].equals("id")) {
                    int id = Integer.parseInt(parte[0]);
                    String nome = parte[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarAluno(String nome) throws IOException {
        int newId = 0;
        FileWriter csvWriter = new FileWriter("./alunos.csv");

        //procura o primeiro valor disponível para criar um id
        for(int i = 100; i < maxId; i++) {
            boolean unico = true;
            for(int id : alunos.keySet() ) {
                if(id == i) {
                    unico = false;
                }
            }
            if(unico) {
                newId = i;
            }
        }
        if(newId == 0) {
            newId = maxId + 1;
        }
        csvWriter.append(String.valueOf(newId));
        csvWriter.append(",");
        csvWriter.append(nome);
        csvWriter.append("\n");
        alunos.put(newId, nome);
    }

}
