package application;

import java.io.*;
import java.util.Scanner;

public class Program {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Arquivo de entrada com os dados originais
        File file = new File("C:\\Users\\Pablo\\Documents\\dados.txt");

        // O usuário informa onde quer salvar o arquivo de saída
        // Ex: C:\Users\Pablo\Documents\out\summary.csv
        System.out.println("Onde deseja criar o novo arquivo: ");
        String newPath = sc.nextLine();

        // Cria o objeto File apontando pro caminho informado
        File fileOut = new File(newPath);

        // Cria a pasta de destino caso ela não exista
        // getParentFile() retorna a pasta pai do arquivo (ex: "out")
        // mkdirs() cria ela e qualquer pasta intermediária que falte
        // Retorna false se já existir — sem problema, não impede a escrita
        fileOut.getParentFile().mkdirs();

        // try-with-resources: abre leitura e escrita juntos
        // Ambos fecham automaticamente ao final, mesmo se der erro
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut))) {

            String line = br.readLine(); // Lê a primeira linha antes do loop
            String[] partes;             // Array que vai guardar as colunas de cada linha
            double total;                // Total unitário de cada item (preço x quantidade)

            while (line != null) {
                // split(",") quebra a linha pelo separador vírgula
                // partes[0] = nome | partes[1] = preço | partes[2] = quantidade
                partes = line.split(",");

                // Converte String para número para poder multiplicar
                // parseDouble para valores decimais, parseInt para inteiros
                total = Double.parseDouble(partes[1]) * Integer.parseInt(partes[2]);

                // Escreve uma linha no arquivo de saída: nome,total
                bw.write(partes[0] + "," + total);
                bw.newLine(); // Pula linha no arquivo (equivalente ao \n)

                line = br.readLine(); // Lê a próxima linha — null quando acabar o arquivo
            }

        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        sc.close();
    }
}