import java.util.List;
import java.util.Scanner;

import models.Sentence;
import operators.FileManager;
import operators.SentenceAnalyser;
import operators.SentencesCreator;
import operators.XmlParser;

public class Main {
	private static void run(String text){
		FileManager.createInputFile(text);

		List<Sentence> sentenceList = SentencesCreator.create(XmlParser.parse());
		for (Sentence sentence : sentenceList){
			SentenceAnalyser.analyse(sentence);
		}
	}

	private static void analyse(){
		List<Sentence> sentenceList = SentencesCreator.create(XmlParser.parse());
		for (Sentence sentence : sentenceList){
			SentenceAnalyser.analyse(sentence);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = "";
		do{
			System.out.println("Wpisz zdanie:");
			line = scanner.nextLine();

			run(line);
		} while (true);
	}
}
