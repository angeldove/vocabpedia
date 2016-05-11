package ksk.com.vocabpedia.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ksk.com.vocabpedia.R;
import ksk.com.vocabpedia.db.DatabaseOperations;
import ksk.com.vocabpedia.model.Word;

/**
 * Created by WbAndroid on 4/18/2016.
 */
public class WordDetail extends AppCompatActivity {

    private TextView edtword;
    private TextView edtmeaning;
    private TextView edtsen1;
    private TextView edtsen2;
    private TextView edtsen3;
    private TextView edtsen4;
    private TextView edtsen5;
    private String word;
    private String meaning;
    private String sen1;
    private String sen2;
    private String sen3;
    private String sen4;
    private String sen5;
    private int wordId;
    ArrayList<Word> MyObject = new ArrayList<Word>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worddetail);

        Bundle extras = getIntent().getExtras();
        wordId = extras.getInt("position");
        Log.d("Project Id","Project id" + wordId);
        edtword = (TextView)findViewById(R.id.editword);
        edtmeaning = (TextView)findViewById(R.id.editmeaning);
        edtsen1 = (TextView)findViewById(R.id.editsentence1);
        edtsen2 = (TextView)findViewById(R.id.editsentence2);
        edtsen3 = (TextView)findViewById(R.id.editsentence3);
        edtsen4 = (TextView)findViewById(R.id.editsentence4);
        edtsen5 = (TextView)findViewById(R.id.editsentence5);

        MyObject= DatabaseOperations.getInstance().getAllDataFromDB();

        Log.d("Project Id", "+++++++MyObject++++++++++++++" + MyObject);
        Log.d("Project Id", "+++++++meaning++++++++++++++" + MyObject.get(wordId).getMeaning());
        Log.d("Project Id", "+++++++sen1++++++++++++++" + MyObject.get(wordId).getSentence1());
        Log.d("Project Id", "+++++++sen2++++++++++++++" + MyObject.get(wordId).getSentence2());
        Log.d("Project Id", "+++++++sen3++++++++++++++" + MyObject.get(wordId).getSentence3());


        word = MyObject.get(wordId).getWord();
        meaning = MyObject.get(wordId).getMeaning();
        sen1 =  MyObject.get(wordId).getSentence1();
        sen2 = MyObject.get(wordId).getSentence2();
        sen3=  MyObject.get(wordId).getSentence3();
        sen4=  MyObject.get(wordId).getSentence4();
        sen5=  MyObject.get(wordId).getSentence5();
        edtmeaning.setText(meaning);
        edtword.setText(word);
        edtsen1.setText(sen1);
        edtsen2.setText(sen2);
        edtsen3.setText(sen3);
        edtsen4.setText(sen4);
        edtsen5.setText(sen5);



    }
}// WordDetail
