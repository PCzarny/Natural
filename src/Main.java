import java.util.List;

import models.Sentence;
import operators.FileManager;
import operators.SentenceAnalyser;
import operators.SentencesCreator;
import operators.XmlParser;

public class Main {
	private static void run(String text){
		FileManager.createInputFile(text);

		System.out.println("Wynik dzia�ania programu:");
		List<Sentence> sentenceList = SentencesCreator.create(XmlParser.parse());
		for (Sentence sentence : sentenceList){
			System.out.println(sentence.toString());
		}
	}

	private static void analyse(){
		List<Sentence> sentenceList = SentencesCreator.create(XmlParser.parse());
		for (Sentence sentence : sentenceList){
			SentenceAnalyser.analyse(sentence);
		}
	}

	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		String line = "";
//		do{
//			System.out.println("Wpisz tekst:");
//			line = scanner.nextLine();
//
//			run(line);
//
//			System.out.println("Wpisz 't' aby kontynuowa�:");
//			line = scanner.nextLine();
//		} while (line.matches("t"));
//		scanner.close();

		analyse();
	}
}
