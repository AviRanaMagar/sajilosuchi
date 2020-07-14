package project.lenovo.slidewalkthroughdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SalesDetails extends AppCompatActivity {

    TextView displaysales;
    //DataBaseHelperSale myDb;
    EditText deletetxt;
    Button deletebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_details);

        //myDb = new DataBaseHelperSale(this);

        deletetxt = (EditText) findViewById(R.id.deletedata);
        deletebutton = (Button) findViewById(R.id.dltbtn);
       /* deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDataSale();
            }
        });*/

        displaysales = (TextView) findViewById(R.id.resultdata);
        //howResult();
    }

    /*private void showResult() {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("बस्तुको नाम: " + res.getString(0) + "\n");
                stringBuffer.append("बस्तु बिभाग : " + res.getString(1) + "\n");
                stringBuffer.append("बस्तुको मात्रा: " + res.getString(2) + "\n");
                stringBuffer.append("बस्तुको मूल्य: " + res.getString(3) + "\n");
                stringBuffer.append("ग्राहक : " + res.getString(4) + "\n" + "\n");
            }
            displaysales.setText(stringBuffer.toString());
            Toast.makeText(this, "दाता लिएको!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "दाता नलिएको !", Toast.LENGTH_LONG).show();
        }
    }*/

   /* private void DeleteDataSale() {
        String name = deletetxt.getText().toString();
        int result = myDb.deleteData(name);
        Toast.makeText(this, result + ":रद्ध भएको !", Toast.LENGTH_LONG).show();
        onRestart();
    }*/

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
        Intent i = new Intent(SalesDetails.this, SalesDetails.class);
        startActivity(i);
        finish();
    }
}
