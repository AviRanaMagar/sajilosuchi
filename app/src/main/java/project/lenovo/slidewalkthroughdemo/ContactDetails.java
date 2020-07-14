package project.lenovo.slidewalkthroughdemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {

    EditText name, number, location;
    Button savecnt, delete;
    DataBaseContact myDb;
    TextView displayclient;
    FloatingActionButton call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        myDb = new DataBaseContact(this);

        call = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel: 9813008459"));
                startActivity(i);
            }
        });

        name = (EditText)findViewById(R.id.nameinput);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(name.length() == 0){
                    name.setError("नाम लेख्नुस");
                }
            }
        });
        number = (EditText)findViewById(R.id.numinput);
        number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(number.length() == 0){
                    number.setError("नम्बर लेख्नुस");
                }
            }
        });
        location = (EditText)findViewById(R.id.locationinput);
        location.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(location.length()==0){
                    location.setError("स्थान लेख्नुस");
                }
            }
        });
        displayclient = (TextView)findViewById(R.id.displaycontact);
        showResult();

        savecnt = (Button)findViewById(R.id.contactsave);
        savecnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.length()==0){
                    Toast.makeText(ContactDetails.this, "नाम लेख्नुस", Toast.LENGTH_LONG).show();
                }else if(number.length() == 0){
                    Toast.makeText(ContactDetails.this, "नम्बर लेख्नुस", Toast.LENGTH_LONG).show();
                }else if(location.length()==0){
                    Toast.makeText(ContactDetails.this, "Eस्थान लेख्नुस", Toast.LENGTH_LONG).show();
                }else {
                    SaveClientContact();
                }
            }
        });
        delete = (Button)findViewById(R.id.ctntdelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }
        });

    }
    private void showResult() {
        Cursor res = myDb.getAllContact();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("नाम : " + res.getString(0) + "\n");
                stringBuffer.append("फोन : " + res.getString(1) + "\n");
                stringBuffer.append("स्थान : " + res.getString(2) + "\n" + "\n");
            }
            displayclient.setText(stringBuffer.toString());
            Toast.makeText(this, "दाता पाईयो !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "दाता पाईयेन !", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteData() {
        String cname = name.getText().toString();
        int result = myDb.deleteData(cname);
        Toast.makeText(this, result+":Rows affected!", Toast.LENGTH_LONG).show();
        onRestart();
    }

    private void SaveClientContact() {
        String cname = name.getText().toString();
        String cphn = number.getText().toString();
        String cloc= location.getText().toString();
        Boolean result = myDb.insertDataConc(cname, cphn, cloc);
        if(result == true){
            Toast.makeText(this, "दाता पाईयो !",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "दाता पाईयेन !",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            startActivity(new Intent(this, PopUp.class));
        } else if (id == R.id.action_calc) {
            startActivity(new Intent(this, Calculator.class));
        } else if (id == R.id.action_refresh) {
            onRestart();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i = new Intent(ContactDetails.this, ContactDetails.class);
        startActivity(i);
        finish();
    }

}
