package project.lenovo.slidewalkthroughdemo;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteTaking extends AppCompatActivity {

    EditText note;
    Button save,clear;
    TextView noteview;
    String memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking);

        noteview = (TextView)findViewById(R.id.displaynote);
        note = (EditText)findViewById(R.id.enternote);
        save = (Button)findViewById(R.id.notesave);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width *.9),(int)(height *.5));

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS",0);
        memo = sharedPreferences.getString("memo"," ");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memo = note.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Memo",memo);
                editor.commit();

                Toast.makeText(NoteTaking.this, "नोट राखियो !", Toast.LENGTH_LONG).show();
                noteview.setText(memo);
                ClickNotification();
            }
        });

        clear = (Button)findViewById(R.id.noteclear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memo = " ";
                note.setText(memo);
            }
        });
    }

    private void ClickNotification() {

            NotificationCompat.Builder mBuilder =(NotificationCompat.Builder)new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.notify)
                    .setContentTitle("नोट ")
                    .setContentText(memo);
            //get an instance of the notificationmanager service
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            //post you notification to the notification bar
            notificationManager.notify(0, mBuilder.build());
        }
    }

