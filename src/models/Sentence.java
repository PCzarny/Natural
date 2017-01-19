package models;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence {
	private List<Word> wordList;

	public Sentence(List<Word> wordList){
		this.wordList = wordList;
	}

	public Sentence(){
		this.wordList = new ArrayList<>();
	}

	public List<Word> getWordList() {
		return wordList;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}

	@Override
	public String toString() {
		String text = "";
		for (Word word : this.wordList) {
			text = text + word.toString() + "\n";
		}
		return text;
	}

	public String parse() {
		String text = "";
		for (Word word : this.wordList) {
			text = text + word.parse() + " ";
		}
		return text;
	}

	public String print() {
		String text = "";
		for (Word word : this.wordList) {
			text = text + word.getOrth() + " ";
		}
		return text;
	}

//	TODO: Can be changed
	public Word getWordById(int id) {
		return this.wordList.stream()
	     .filter(word -> word.getId() == id)
	     .collect(Collectors.toList())
	     .get(0);
	}

	public Boolean removeWordById(int id) {
		return this.wordList.remove(this.getWordById(id));
	}

	public Sentence createSentence(String tags) {
		Sentence subsentence = new Sentence();
		String[] wordTags = tags.split(" ");

		for(String word : wordTags) {
			int wordId;
			int index = word.indexOf(":id");
			if (index >= 0 && index + 3 < word.length()) {
				try {
					wordId = Integer.parseInt(word.substring(index + 3));
					subsentence.getWordList().add(this.getWordById(wordId));
				} catch (Exception ex) {
					System.out.println(ex.toString());
				}
			} else {
				if(word.matches("[a-z]+")) {
					subsentence.getWordList().add(new Word(word, word, "conj"));
				} else {
					subsentence.getWordList().add(new Word(word, word, "interp"));
				};
			}
		}

		return subsentence;
	}
}
