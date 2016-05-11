package ksk.com.vocabpedia.model;

/*
 * model for task data
 */
public class Favourite {

    private Long id;
    private Long wordid;

    public Favourite() {
    }

    public Favourite(Long id, Long wordid) {
        this.id = id;
        this.wordid = wordid;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setWordId(Long word) {
        this.wordid = word;
    }

    public Long getWordId() {
        return wordid;
    }
}// Task
