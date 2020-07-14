package project.lenovo.slidewalkthroughdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton datain, dataout, purdetail, saledetail, graph, info, calulator, note, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datain = (ImageButton) findViewById(R.id.imagein);
        datain.setOnClickListener(this);
        dataout = (ImageButton) findViewById(R.id.imageout);
        dataout.setOnClickListener(this);
        purdetail = (ImageButton) findViewById(R.id.imageinvent);
        purdetail.setOnClickListener(this);
        saledetail = (ImageButton) findViewById(R.id.imagesales);
        saledetail.setOnClickListener(this);
        graph = (ImageButton) findViewById(R.id.imageanalyse);
        graph.setOnClickListener(this);
        info = (ImageButton) findViewById(R.id.imageinfo);
        info.setOnClickListener(this);
        calulator = (ImageButton) findViewById(R.id.imagecalcula);
        calulator.setOnClickListener(this);
        note = (ImageButton) findViewById(R.id.imagenote);
        note.setOnClickListener(this);
        contact = (ImageButton) findViewById(R.id.savecntct);
        contact.setOnClickListener(this);

    }
        @Override
        public void onClick(View v) {

            if(v == datain){
                startActivity(new Intent(this, DataInput.class));
            }else if(v == dataout){
                startActivity(new Intent(this, DataOutput.class));
            }else if(v == saledetail){
                startActivity(new Intent(this, SalesDetails.class));
            }else if(v == purdetail){
                startActivity(new Intent(this, PurchaseDetails.class));
            }else if(v == graph){
                startActivity(new Intent(this, Graph.class));
            }else if(v == info){
                startActivity(new Intent(this, Information.class));
            }else if(v == note){
                startActivity(new Intent(this, NoteTaking.class));
            }else if(v == calulator){
                startActivity(new Intent(this, Calculator.class));
            }else if(v == contact){
                startActivity(new Intent(this, ContactDetails.class));
            }
        }

        @Override
        public void onBackPressed(){
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("पुस्टि गर्नुहोस !");
            alert.setIcon(R.drawable.wanttoexit);
            alert.setMessage("के तपाई बाहिर निस्किन चाहनु हुन्छ ?");
            alert.setCancelable(false);
            alert.setPositiveButton("हुन्छ ", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.setNegativeButton("हुदैन ", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alert.show();
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
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
