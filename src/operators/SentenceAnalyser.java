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
		List<Sentence> simpleFacts = new ArrayList<>();
		List<Sentence> outputSentences = new ArrayList<>();

		simpleFacts.addAll(removeAllAdjectives(sentence));

		outputSentences.addAll(splitAllComplex(sentence));

		outputSentences.addAll(splitAllSubstances(outputSentences));

//		Return array of facts inside outputSentences and modified sentence
		System.out.println("\nFakty elementarne:");
		for(Sentence fact : simpleFacts) {
			System.out.println(fact.print());
		}
		for(Sentence fact : outputSentences) {
			System.out.println(fact.print());
		}
		System.out.println("--------------");
		return outputSentences;
	}

//	Till any adjective - substance collocation exist => create simple facts
	public static List<Sentence> removeAllAdjectives(Sentence sentence) {
		Sentence foundFact;
		List<Sentence> simpleFacts = new ArrayList<Sentence>();

		while (true) {
			foundFact = removeOneAdjective(sentence);
			if (foundFact == null) {
				break;
			}
			simpleFacts.add(foundFact);
		}

		return simpleFacts;
	}
	public static Sentence removeOneAdjective(Sentence sentence) {
		Sentence fact = new Sentence();
		String grammarForms = sentence.parse();
	    String pattern = "(adj:(sg|pl)\\S*:id([0-9]+) (, )?)(subst:\\2\\S*:id([0-9]+) )";

//	    TaKiPi have wrong tags :/
//	    String pattern = "(adj:((?:sg|pl):[a-z]+:(?:[mnf][1-3]))\\S*:id([0-9]+) (, )?)(subst:\\2\\S*:id([0-9]+) )";

	    Matcher m = Pattern.compile(pattern).matcher(grammarForms);

		if (m.find( )) {
//			TODO: Check if parse properly
			int adjectiveId = Integer.parseInt(m.group(3));
			int substanceId = Integer.parseInt(m.group(6));


			Boolean followedByComma = m.group(4) != null;


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
//			System.out.println("NO MATCH");
		}

		return null;
	}

	public static List<Sentence> splitAllSubstances(List<Sentence> sentence) {
		List<Sentence> outputSentences = new ArrayList<>();
		List<Sentence> foundFacts;

		outputSentences.addAll(sentence);

		for (int i = 0; i < outputSentences.size(); i++) {
			foundFacts = splitSubstances(outputSentences.get(i));

//			If current sentence was divided into 2 smaller, is not final and can be removed
			if (foundFacts != null && foundFacts.size() == 2) {
				outputSentences.remove(i);
				i--;
			}

			outputSentences.addAll(foundFacts);
		}
		return outputSentences;
	}
	public static List<Sentence> splitSubstances(Sentence sentence) {
		List<Sentence> outputSentences = new ArrayList<>();
		String grammarForms = sentence.parse();

//		TODO: Check others conjuctions
	    String pattern = "(subst:\\S*:id[0-9]+ )((?:[,i] subst:\\S*:id[0-9]+ )+)";

	    Matcher m = Pattern.compile(pattern).matcher(grammarForms);

		if (m.find( )) {
			String firstSubst = m.group(1);
			String nextSubsts = m.group(2);

//			Create sentences only with the first substance (from detected group) and without first
//			TODO: Check others conjuctions
			outputSentences.add(sentence.createSentence(grammarForms.replaceAll(firstSubst + "[,i] " , "")));
			outputSentences.add(sentence.createSentence(grammarForms.replaceAll(nextSubsts, "")));

		} else {
//			System.out.println("NO MATCH");
		}

		return outputSentences;
	}

	public static List<Sentence> splitAllComplex(Sentence sentence) {
		List<Sentence> outputSentences = new ArrayList<>();
		List<Sentence> foundFacts;

		outputSentences.add(sentence);

		for (int i = 0; i < outputSentences.size(); i++) {
			foundFacts = splitComplex(outputSentences.get(i));

//			If current sentence was divided into 2 smaller, is not final and can be removed
			if (foundFacts != null && foundFacts.size() == 2) {
				outputSentences.remove(i);
				i--;
			}

			outputSentences.addAll(foundFacts);
		}
		return outputSentences;
	}
	public static List<Sentence> splitComplex(Sentence sentence) {
		List<Sentence> list = new ArrayList<Sentence>();
		String grammarForms = sentence.parse();

//		TODO: niekoniecznie zaczyna sie od subject
		String verb = "(?:fin|praet)";
		String subject = "(?:subst|ppron12|ppron3)";

		String pattern = String.format("((%s:(sg|pl):[a-z]+:([mnf])((?!%s).)* %s:\\3((?!%s).)*) (i|, ale|, kiedy|, bo|, gdy|, kiedy|, ponieważ|, bowiem) )((?!%s).)*%s",
				subject,
				verb,
				verb,
				verb,
				verb,
				verb);

	    Matcher m = Pattern.compile(pattern).matcher(grammarForms);

	    if (m.find()) {
			String first = m.group(2);
			String second = grammarForms.replaceAll(m.group(1), "");

			Sentence firstSentence = sentence.createSentence(first);
			Sentence restSentence = sentence.createSentence(grammarForms.replaceAll(m.group(1), ""));

			firstSentence.setSubject(firstSentence.findSubject());
			if(restSentence.findSubject() == null) {
				restSentence.setSubject(firstSentence.getSubject());
//				TODO: check if action is compatible with new subject
				restSentence.getWordList().add(0, firstSentence.getSubject());
			} else {
				restSentence.setSubject(restSentence.findSubject());
			}

			list.add(firstSentence);
			list.add(restSentence);
		}

		return list;
	}

}
