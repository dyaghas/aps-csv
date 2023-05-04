package mainpack;

import java.util.Scanner;

public interface CsvInterface {
    void lerCsv();
    void processaLinhaCsv(String linha);
    void listarCsv();
    void cadastrarDadoCsv(Scanner scan);
}
