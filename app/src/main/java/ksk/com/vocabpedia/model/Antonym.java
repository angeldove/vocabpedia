package ksk.com.vocabpedia.model;

/**
 * Created by wb12 on 4/26/2016.
 */
public class Antonym {

    private Long id;
    private String word1;
    private String word2;

    Antonym() {
        //empty constructor
    }

   public Antonym(Long id, String word1, String word2) {
        this.id = id;
        this.word1 = word1;
        this.word2 = word2;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }
}//Antonym
