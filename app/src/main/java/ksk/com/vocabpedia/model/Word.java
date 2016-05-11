package ksk.com.vocabpedia.model;

import java.io.Serializable;

/*
 * model for project id and title
 */
@SuppressWarnings("serial")
public class Word implements Serializable {

	private Long   id = null;
	private String word = null;
	private String meaning = null;
	private String sentence1 = null;
	private String sentence2 = null;
	private String sentence3 = null;
	private String sentence4 = null;
	private String sentence5 = null;


	public Word() {

	}

	public Word(Long id, String word, String meaning,String sentence1,String sentence2,String sentence3,String sentence4, String sentence5) {
		this.id = id;
		this.word = word;
		this.meaning = meaning;
		this.sentence1 = sentence1;
		this.sentence2 = sentence2;
		this.sentence3 = sentence3;
		this.sentence4 = sentence4;
		this.sentence5 = sentence5;
	}

	public void setId(Long ID) {
		this.id = ID;
	}

	public Long getId() {
		return id;
	}


	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getSentence1() {
		return sentence1;
	}

	public void setSentence1(String sentence1) {
		this.sentence1 = sentence1;
	}

	public String getSentence2() {
		return sentence2;
	}

	public void setSentence2(String sentence2) {
		this.sentence2 = sentence2;
	}

	public String getSentence3() {
		return sentence3;
	}

	public void setSentence3(String sentence3) {
		this.sentence3 = sentence3;
	}

	public String getSentence4() {
		return sentence4;
	}

	public void setSentence4(String sentence4) {
		this.sentence4 = sentence4;
	}

	public String getSentence5() {
		return sentence5;
	}

	public void setSentence5(String sentence5) {
		this.sentence5 = sentence5;
	}
}// Word
