package operators;

import java.util.ArrayList;
import java.util.List;

import models.Sentence;
import models.Word;
import xml_models.CesAna;
import xml_models.CesAna.ChunkList.Chunk;
import xml_models.CesAna.ChunkList.Chunk.Tok;

public class SentencesCreator {
	public static List<Sentence> create(CesAna cesAna){
		List<Sentence> sentenceList = new ArrayList<>();
		for (Chunk chunk : cesAna.getChunkList().getChunk()){
			Sentence sentence = new Sentence();
			for (Tok tok : chunk.getTokList()){
				Word word = new Word(tok.getOrth(), tok.getLex().getBase(), tok.getLex().getCtag());
				sentence.getWordList().add(word);
			}
			sentenceList.add(sentence);
		}
		return sentenceList;
	}
}
