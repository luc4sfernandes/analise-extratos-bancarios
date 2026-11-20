import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// Analisador Simples de Transações Bancárias
public class BankTransactionAnalyzerSimple {

	private static final String RESOURCES = "/home/Lucas/Programação/analise-extratos-bancarios/src/main/resources/"; // Variavel contante do caminho até a pasta

	public static void main(final String[] args) throws IOException { 

		final Path path = Paths.get(RESOURCES + "bank-data-simple.csv"); // Converte o caminho em um objeto tipo Path
		final List<String> lines = Files.readAllLines(path); // Abre o arquivo e lé cada linha e põe na lista

		double total = 0d; // '0d' -> Tratar como double não como int

		for(final String line: lines){
			final String[] columns = line.split(","); // trasforma em um array de strings com base no ','

			// System.out.println(Arrays.toString(columns)); 
			
			final double amount = Double.parseDouble(columns[1]); // trasforma a string em Double
			total += amount; // Soma
		}

		System.out.println("The total for all transactions is " + total);	

		Map<Integer, Double> month = new HashMap<>();
		for(String line: lines){
			String[] columns = line.split(",");
			String[] tempString = columns[0].split("-");
			
			if(Integer.parseInt(tempString[1]) == 1){
				month.merge(Integer.parseInt(tempString[1]), Double.parseDouble(columns[1]), Double::sum);	
			}

			if(Integer.parseInt(tempString[1]) == 2){
				month.merge(Integer.parseInt(tempString[1]), Double.parseDouble(columns[1]), Double::sum);	
			}
		}

		System.out.printf("January Total Bank Transactions: %.2f\n", month.get(1));
		System.out.printf("February Total Bank Transactions: %.2f\n", month.get(2));
	}
}
