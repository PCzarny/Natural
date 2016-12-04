package operators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Sentence;
import models.Word;

public class SentenceAnalyser {
	public static Word toBe = new Word("jest", "jest", "");
	
	public static List<Sentence> analyse(Sentence sentence) {
		List<Sentence> outputSentences = new ArrayList<>();
		System.out.println("Zdanie wejściowe: " + sentence.print());
	
//		Till any adjective - substance collocation exist => create simple facts
//		Sentence foundFact;
//		do {
//			foundFact = removeAdjectives(sentence);
//			outputSentences.add(foundFact);
//		} while(foundFact != );
		
		List<Sentence> foundFacts;
		outputSentences.add(sentence);
		for (int i = 0; i < outputSentences.size(); i++) {
			System.out.println(outputSentences.get(i).print());
			foundFacts = splitSubstances(outputSentences.get(i));
			outputSentences.addAll(foundFacts);
			System.out.println("Rozmiar listy" + outputSentences.size());
		}
		
//		Return array of facts inside outputSentences and modified sentence
		System.out.println("Wynik działania");
		for(Sentence fact : outputSentences) {
			System.out.println(fact.print());
		}
		System.out.println(sentence.print());
		return outputSentences;
	}
	
	public static Sentence removeAdjectives(Sentence sentence) {
		Sentence fact = new Sentence();
		String grammarForms = sentence.parse();
	    String pattern = "(adj:((?:sg|pl):[a-z]+:(?:[mnf][1-3]))\\S*:id([0-9]+) (, )?)(subst:\\2\\S*:id([0-9]+) )";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(grammarForms);
		      
		if (m.find( )) {			
//			TODO: Check if parse properly
			int adjectiveId = Integer.parseInt(m.group(3));
			int substanceId = Integer.parseInt(m.group(6));
			
			
			Boolean followedByComma = m.group(4) != null;
			
			System.out.println("Adj ID: " + adjectiveId );
			System.out.println("Subst ID: " + substanceId );
			
//			Create fact => substance + be + adjective
			fact.getWordList().add(sentence.getWordById(substanceId));
			fact.getWordList().add(toBe);
			fact.getWordList().add(sentence.getWordById(adjectiveId));
			
//			Remove comma after adjective - if exist
			if (followedByComma) {
				int indexOfAdj = sentence.getWordList().indexOf(sentence.getWordById(adjectiveId));
				if (sentence.getWordList().get(indexOfAdj + 1) != null &&
						sentence.getWordList().get(indexOfAdj + 1).getBase().equals(",")) {
					sentence.getWordList().remove(indexOfAdj + 1);
				}
			}
			
			sentence.removeWordById(adjectiveId);
			return fact;
		} else {
			System.out.println("NO MATCH");
		}
		
		return null;
	}

	public static List<Sentence> splitSubstances(Sentence sentence) {
		List<Sentence> outputSentences = new ArrayList<>();
		String grammarForms = sentence.parse();		
	    
	    String pattern = "(subst:\\S*:id[0-9]+) ((?:[,i] subst:\\S*:id[0-9]+ )+)";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(grammarForms);
		      
		if (m.find( )) {
			String matched = m.group(0);
						
//			TODO: Finish separation
			outputSentences.add(sentence.createSubsentence(grammarForms.replaceAll(m.group(1), "")));
			outputSentences.add(sentence.createSubsentence(grammarForms.replaceAll(m.group(2), "")));

			
		} else {
			System.out.println("NO MATCH");
		}

		return outputSentences;
	}
}
