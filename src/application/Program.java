package application;

import java.io.*;
import java.util.Scanner;

public class Program {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File file = new File("C:\\Users\\Pablo\\Documents\\dados.txt");
        System.out.println("Onde deseja criar o novo arquivo: ");
        String newPath = sc.nextLine(); // Como o nome do arquivo já é conhecido, basta informar o caminho onde ele deve ser criado. Ex: C:\\Users\\Pablo\\Documents\\out\\summary.txt
        File fileOut = new File(newPath); // Criando o arquivo de saída, utilizando o caminho informado pelo usuário. O nome do arquivo é "summary.csv",
                                            // mas pode ser alterado conforme a necessidade. O importante é que o caminho seja válido e que o programa tenha permissão para criar arquivos nesse local.
        System.out.println(fileOut.getAbsolutePath()); // Exibindo o caminho absoluto do arquivo de saída para confirmar que o caminho foi interpretado corretamente.
                                                        // Isso é útil para verificar se o caminho informado pelo usuário está correto e se o programa está apontando para o local desejado.
        System.out.println(fileOut.getParentFile().mkdirs()); // Criando os diretórios necessários para o arquivo de saída, caso eles não existam. O método mkdirs() cria todos os diretórios necessários para o caminho especificado.
                                                                // Se os diretórios já existirem, ele simplesmente retorna false, mas isso não impede a criação do arquivo de saída.
                                                                // É importante garantir que o programa tenha permissão para criar diretórios e arquivos no local especificado.
        boolean mkdirs = fileOut.getParentFile().mkdirs();


        try (BufferedReader br = new BufferedReader(new FileReader(file));
         BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut))) { // Utilizando try-with-resources para garantir que os recursos sejam fechados automaticamente. O BufferedReader é usado para ler o arquivo de entrada, e o BufferedWriter é usado para escrever no arquivo de saída.

           String line = br.readLine();
           String[] partes;
           double total;

           while (line != null){
               partes = line.split(",");
               System.out.println(partes[0] + " R$" + partes[1]);
               total = Double.parseDouble(partes[1]) * Integer.parseInt(partes[2]);
               System.out.println(total);
               bw.write(partes[0] +" R$ "+ total);
               line = br.readLine();
               bw.newLine();
           }

       }  catch (IOException e){
           System.out.println("ERRO: " + e.getMessage());
       }
        System.out.println();
sc.close();
    }
}
