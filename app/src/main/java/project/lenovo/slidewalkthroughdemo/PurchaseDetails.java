package project.lenovo.slidewalkthroughdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PurchaseDetails extends AppCompatActivity {

    TextView displaydata;
    DataBaseHelper myDb;
    EditText deletetxt;
    Button deletebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);
        myDb = new DataBaseHelper(this);

        deletetxt = (EditText) findViewById(R.id.deletedata);
        deletebutton = (Button) findViewById(R.id.dltbtn);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }
        });

        displaydata = (TextView) findViewById(R.id.resultdata);
        showResult();
    }

    private void showResult() {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("बस्तुको नाम: " + res.getString(0) + "\n");
                stringBuffer.append("बस्तु बिभाग: " + res.getString(1) + "\n");
                stringBuffer.append("बस्तुको मात्रा: " + res.getString(2) + "\n");
                stringBuffer.append("बस्तुको मूल्य: " + res.getString(3) + "\n");
                stringBuffer.append("ग्राहक : " + res.getString(4) + "\n"+ "\n");
            }
            displaydata.setText(stringBuffer.toString());
            Toast.makeText(this, "दाता लिएको !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "दाता नलिएको !", Toast.LENGTH_LONG).show();
        }
    }
    private void DeleteData(){
        String name = deletetxt.getText().toString();
        int result = myDb.deleteData(name);
        Toast.makeText(this, result+":रद्ध भएको !", Toast.LENGTH_LONG).show();
        onRestart();
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
            startActivity(new Intent(this, DataInput.class));
        } else if (id == R.id.action_calc) {
            startActivity(new Intent(this, PurchaseDetails.class));
        } else if (id == R.id.action_refresh) {
            onRestart();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i = new Intent(PurchaseDetails.this, PurchaseDetails.class);
        startActivity(i);
        finish();
    }
}
