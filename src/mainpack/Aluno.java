package mainpack;

import java.io.*;
import java.util.HashMap;

public class Aluno {
    private int maxId;
    File alunoCsv = new File("./alunos.csv");

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
                    setMaxId(id);
                    alunos.put(id, nome);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //exibe as informações do arquivo csv
    public void exibirCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(alunoCsv))) {
            System.out.println("id" + " " + "nome");
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parte = linha.split(",");
                //condicional que pula a primeira linha do arquivo csv para
                //evitar parse do nome da coluna para int
                if(!parte[0].equals("id")) {
                    int id = Integer.parseInt(parte[0]);
                    String nome = parte[1];
                    System.out.println(id + " " + nome);
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarAluno(String nome) throws IOException {
        FileWriter fr = new FileWriter("./alunos.csv", true);
        BufferedWriter br = new BufferedWriter(fr);
        int newId = getNewId();
        br.newLine();
        br.write(newId + "," + nome);
        alunos.put(newId, nome);
        System.out.println(newId + " " + nome);
        br.close();
        //leitura para impedir que dados sejam sobrescritos ao serem alterados enquanto o código está em execução
        lerCsv();
    }

    //procura o primeiro valor disponível para criar um id
    public int getNewId() {
        int newId = 0;
        for(int i = 1; i < maxId; i++) {
            boolean unico = true;
            for(int id : alunos.keySet() ) {
                System.out.println(id);
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
        return newId;
    }

    public void setMaxId(int id) {
        if(id > maxId) {
            maxId = id;
        }
    }
}
