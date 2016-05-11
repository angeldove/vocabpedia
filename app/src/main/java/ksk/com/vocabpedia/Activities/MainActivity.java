package ksk.com.vocabpedia.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import ksk.com.vocabpedia.R;
import ksk.com.vocabpedia.db.DaoMaster;
import ksk.com.vocabpedia.db.DatabaseManager;
import ksk.com.vocabpedia.db.DatabaseOperations;
import ksk.com.vocabpedia.model.Word;

// This is main class for list of words

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    Toolbar tooBar;
    ListView wordlist;
    private ImageView addword;
    private EditText word;
    private EditText meaning;
    private EditText sen1;
    private EditText sen2;
    private EditText sen3;
    private EditText sen4;
    private EditText sen5;
    private Button cancel;
    private Button save;
   // private ImageView taskadd;
    private ImageView addproject;
    ArrayList<Word> objectWordModel;
    WordAdapter adapter;

    long projectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
       // getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        tooBar = (Toolbar) findViewById(R.id.toolbar);
        wordlist = (ListView) findViewById(R.id.listwords);
        addword = (ImageView) findViewById(R.id.imgaddword);
        addproject = (ImageView) findViewById(R.id.addproject);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, " ", null);
        DatabaseManager.initializeInstance(helper);

        objectWordModel = DatabaseOperations.getInstance().getAllDataFromDB();

        addword.setOnClickListener(this);
        wordlist.setOnItemClickListener(this);
        wordlist.setOnItemLongClickListener(this);
        if (objectWordModel.size() == 0)
            addproject.setVisibility(View.VISIBLE);
        else
            addproject.setVisibility(View.INVISIBLE);
        onResume();
    }

    // dialog to add new word
    public void showWordDialog(final Word item) {
        final Dialog dBox = new Dialog(MainActivity.this);

        dBox.setContentView(R.layout.add_project);

        dBox.setTitle(Html.fromHtml("<font color='#009688'>Add Word</font>"));

        word        = (EditText) dBox.findViewById(R.id.edtword);
        meaning     = (EditText) dBox.findViewById(R.id.edtmeaning);
        sen1        = (EditText) dBox.findViewById(R.id.edtsen1);
        sen2        = (EditText) dBox.findViewById(R.id.edtsen2);
        sen3        = (EditText) dBox.findViewById(R.id.edtsen3);
        sen4        = (EditText) dBox.findViewById(R.id.edtsen4);
        sen5        = (EditText) dBox.findViewById(R.id.edtsen5);
        save        = (Button) dBox.findViewById(R.id.btnsave);
        cancel      = (Button) dBox.findViewById(R.id.btncancel);

        if (item != null) {
            word.setText(item.getWord());
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBox.cancel();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (word.getText().toString().equals("")
                        || word.getText().toString().isEmpty()) {
                    word.setError("Enter Word");
                } else if (meaning.getText().toString().equals("")
                        || meaning.getText().toString().isEmpty()) {
                    meaning.setError("Enter meaning");
                } /*else if (sen1.getText().toString().equals("")
                        || sen1.getText().toString().isEmpty()) {
                    sen1.setError("Enter Sentence 1");
                } else if (sen2.getText().toString().equals("")
                        || sen2.getText().toString().isEmpty()) {
                    sen2.setError("Enter Sentence 2");
                } else if (sen3.getText().toString().equals("")
                        || sen3.getText().toString().isEmpty()) {
                    sen3.setError("Enter Sentence 3");
                } else if (sen4.getText().toString().equals("")
                        || sen4.getText().toString().isEmpty()) {
                    sen4.setError("Enter Sentence 4");
                } else if (sen5.getText().toString().equals("")
                        || sen5.getText().toString().isEmpty()) {
                    sen5.setError("Enter Sentence 5");
                } */else {

                    Word data = new Word();

                    data.setWord(word.getText().toString());
                    data.setMeaning(meaning.getText().toString());
                    data.setSentence1(sen1.getText().toString());
                    data.setSentence2(sen2.getText().toString());
                    data.setSentence3(sen3.getText().toString());
                    data.setSentence4(sen4.getText().toString());
                    data.setSentence5(sen5.getText().toString());

                    if (item != null) {
                        data.setId(item.getId());
                        DatabaseOperations.getInstance().UpdateData(data);
                        setListAdapter();
                    } else {
                        DatabaseOperations.getInstance().insert(data);
                        setListAdapter();
                    }

                    dBox.cancel();
                }
            }
        });
        dBox.show();
    }
    // set list adapter
    public void setListAdapter() {

        objectWordModel = DatabaseOperations.getInstance().getAllDataFromDB();

        adapter = new WordAdapter(MainActivity.this, objectWordModel);
        adapter.notifyDataSetChanged();
        wordlist.setAdapter(adapter);
        wordlist.setOnItemClickListener(this);
        wordlist.setOnItemLongClickListener(this);

        if (objectWordModel.size() == 0)
            addproject.setVisibility(View.VISIBLE);
        else
            addproject.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
     //   Config.OKANE(this, revmob);
        projectId = objectWordModel.get(position).getId();
        Bundle bundle = new Bundle();

        bundle.putInt("position", position);
        bundle.putString("name", objectWordModel.get(position).getWord());

        Intent it = new Intent(MainActivity.this, WordDetail.class);
        it.putExtras(bundle);
        startActivity(it);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.imgaddword :
                showWordDialog(null);

        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (objectWordModel.size() == 0)
            addproject.setVisibility(View.VISIBLE);
        else
            addproject.setVisibility(View.INVISIBLE);
        setListAdapter();
    }
}//MainActivity
