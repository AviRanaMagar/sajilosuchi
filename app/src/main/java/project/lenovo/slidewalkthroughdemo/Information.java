package project.lenovo.slidewalkthroughdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static project.lenovo.slidewalkthroughdemo.R.id.searchview;

public class Information extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView searchView;
    HashMap<String, List<String>> myHeader;
    List<String> myChild;
    ExpandableListView expand;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        expand = (ExpandableListView)findViewById(R.id.expandablelist);
        myHeader = MyAdapter.DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new MyAdapter(this, myHeader, myChild);
        expand.setAdapter(adapter);
        searchView = (SearchView) findViewById(searchview);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        //adaptera.getFilter().filter(newText);
        Toast.makeText(this, "खोज : " + text, Toast.LENGTH_LONG).show();
        return false;
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
        Intent i = new Intent(Information.this, Information.class);
        startActivity(i);
        finish();
    }
}
class MyAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private HashMap<String, List<String>> ChildTitles;
    private List<String> HeaderTitles;

    MyAdapter(Context ctx, HashMap<String, List<String>> ChildTitles, List<String> HeaderTitles) {
        this.ctx = ctx;
        this.ChildTitles = ChildTitles;
        this.HeaderTitles = HeaderTitles;
    }

    @Override
    public int getGroupCount() {
        return HeaderTitles.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return ChildTitles.get(HeaderTitles.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return HeaderTitles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return ChildTitles.get(HeaderTitles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title = (String) this.getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_header, null);
        }
        TextView txt = (TextView) view.findViewById(R.id.expandabletitle);
        txt.setTypeface(null, Typeface.BOLD);
        txt.setText(title);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String title = (String) this.getChild(i, i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_children, null);
        }
        TextView txt = (TextView) view.findViewById(R.id.expandabledesc);
        txt.setText(title);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class DataProvider {
        public static HashMap<String, List<String>> getInfo() {
            HashMap<String, List<String>> HeaderDetails = new HashMap<String, List<String>>();
            List<String> ChildDetails1 = new ArrayList<String>();
            ChildDetails1.add("बस्तु बिभाग: खाना ");
            ChildDetails1.add("मात्रा: १०  ");
            ChildDetails1.add("बस्तुको मूल्य: १००० ");
            ChildDetails1.add("ग्राहक: सिता ");
            ChildDetails1.add("मिति : 05/20/2017");
            List<String> ChildDetails2 = new ArrayList<String>();
            ChildDetails2.add("बस्तु बिभाग: बस्त्र ");
            ChildDetails2.add(" मात्रा:१० ");
            ChildDetails2.add("बस्तुको मूल्य: १५००० ");
            ChildDetails2.add("ग्राहक : राम ");
            ChildDetails2.add("मिति :  05/20/2017 ");

            HeaderDetails.put("कुकिज ", ChildDetails1);
            HeaderDetails.put(" जकेट  ", ChildDetails2);

            return HeaderDetails;
        }

    }
}
