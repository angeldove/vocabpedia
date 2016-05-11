package ksk.com.vocabpedia.Activities;

/**
 * Created by wb12 on 4/26/2016.
 */
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import ksk.com.vocabpedia.R;
        import ksk.com.vocabpedia.db.DatabaseOperations;
        import ksk.com.vocabpedia.model.Word;

//setting adapter to client list
public class WordAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    ArrayList<Word> object = new ArrayList<Word>();

    public WordAdapter(Context c, ArrayList<Word> object) {
        this.context = c;
        this.object = object;
    }

    @Override
    public int getCount() {
        Log.d("Size", "Word Values "+object.size());
        return object.size();
    }

    @Override
    public Object getItem(int position) {
        return object.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ArrayList<Word>   stepobject;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_row, null);

        long Id    = object.get(position).getId();

        TextView projectTitle    = (TextView) convertView.findViewById(R.id.txtTitle);
      //  TextView txttodo    = (TextView) convertView.findViewById(R.id.txttodo);
        //TextView txtdoing    = (TextView) convertView.findViewById(R.id.txtdoing);
        //TextView txtdone    = (TextView) convertView.findViewById(R.id.txtdone);
        //TextView txtdate    = (TextView) convertView.findViewById(R.id.txtdate);

        //Log.d("Task Value", "Task Here "+ object.get(position).getTitle());

        projectTitle.setText(object.get(position).getWord());

        //txttodo.setText(""+ DatabaseOperations.getInstance().getTaskTdoCountByClient(Id));
       // txtdoing.setText(""+DatabaseOperations.getInstance().getTaskDoingCountByClient(Id));
       // txtdone.setText(""+DatabaseOperations.getInstance().getTaskDoneCountByClient(Id));
       // txtdate.setText("" +getDate(object.get(position).getDate(), position));


        return convertView;
    }
    //
    private String getDate(long timeStamp , int position){

        try{
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date netDate = (new Date(timeStamp * 1000));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}
