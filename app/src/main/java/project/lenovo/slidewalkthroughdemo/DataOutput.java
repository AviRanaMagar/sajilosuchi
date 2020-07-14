package project.lenovo.slidewalkthroughdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataOutput extends AppCompatActivity {

    Spinner spinner;
    EditText getpname, getpqnty, getprate, getpsupplier;
    Button savebtn;
    ImageButton picture, gallery;
    private static final int CAMERA_REQUEST = 123;
    private final static int SELECT_PHOTO = 12345;
    ImageView imagecapture;
    DataBaseHelperSale myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_output);

        myDb = new DataBaseHelperSale(this);

        Calendar calendar;
        SimpleDateFormat simpleDateFormat;
        String date;

        spinner = (Spinner)findViewById(R.id.editcategory);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.division,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        getpname = (EditText)findViewById(R.id.editproductname);
        getpqnty = (EditText)findViewById(R.id.editprice);
        getprate = (EditText)findViewById(R.id.editprice);
        getpsupplier = (EditText)findViewById(R.id.suppliername);

        picture = (ImageButton)findViewById(R.id.takepic);
        gallery = (ImageButton)findViewById(R.id.browseimage);
        imagecapture = (ImageView)findViewById(R.id.captureimage);

        savebtn = (Button)findViewById(R.id.savebutton);

        getpname = (EditText) findViewById(R.id.editproductname);
        getpname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (getpname.getText().length() == 0) {
                    getpname.setError("बस्तुको नाम");
                }
            }
        });
        getpqnty = (EditText) findViewById(R.id.editquantity);
        getpqnty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (getpqnty.getText().length() == 0) {
                    getpqnty.setError("बस्तुको मात्रा");
                }
            }
        });
        getprate = (EditText) findViewById(R.id.editprice);
        getprate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (getprate.getText().length() == 0) {
                    getprate.setError("बस्तुको मूल्य");
                }
            }
        });
        getpsupplier = (EditText) findViewById(R.id.suppliername);
        getpsupplier.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (getpsupplier.getText().length() == 0) {
                    getpsupplier.setError("ग्राहक ");
                }
            }
        });

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveData();
                if (getpname.length() == 0) {
                    Toast.makeText(DataOutput.this, "बस्तुको नाम", Toast.LENGTH_LONG).show();
                } else if (getpqnty.length() == 0) {
                    Toast.makeText(DataOutput.this, "बस्तुको मात्रा", Toast.LENGTH_LONG).show();
                } else if (getprate.length() == 0) {
                    Toast.makeText(DataOutput.this, "बस्तुको मूल्य", Toast.LENGTH_LONG).show();
                } else if (getpsupplier.length() == 0) {
                    Toast.makeText(DataOutput.this, "ग्राहक ", Toast.LENGTH_LONG).show();
                }else
                {
                    //savedatabase();
                    Toast.makeText(DataOutput.this, "दाता राखियो !",Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void savedatabase() {
        String pname = getpname.getText().toString();
        String pcategory = spinner.getSelectedItem().toString();
        String pqnty = getpqnty.getText().toString();
        String prate = getprate.getText().toString();
        String psupply = getpsupplier.getText().toString();

        Boolean result = myDb.insertDataSale(pname,pcategory,pqnty,prate, psupply);
        if(result == true){
            Toast.makeText(this, "दाता राखियो !",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "दाता राखिएन !",Toast.LENGTH_LONG).show();
        }
    }

   /* private void btnCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imagecapture.setImageBitmap(photo);
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
        Intent i = new Intent(DataOutput.this, DataOutput.class);
        startActivity(i);
        finish();
    }

}
