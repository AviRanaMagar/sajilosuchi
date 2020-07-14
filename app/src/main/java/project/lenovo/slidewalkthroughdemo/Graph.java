package project.lenovo.slidewalkthroughdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

public class Graph extends AppCompatActivity {

    private static final String TAG = "GraphActivity";
    PointsGraphSeries<DataPoint> xySeries;
    private Button point;
    private EditText xval, yval;
    GraphView mScatterPlot;
    private ArrayList<XYValues> xyValuesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        point = (Button) findViewById(R.id.seegraphplot);
        xval = (EditText) findViewById(R.id.xinput);
        yval = (EditText) findViewById(R.id.yinput);
        mScatterPlot = (GraphView) findViewById(R.id.graph);
        xyValuesArray = new ArrayList<>();
        init();
    }

    private void init() {
        xySeries = new PointsGraphSeries<>();
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!xval.getText().toString().equals(" ") && !yval.getText().toString().equals(" ")) {
                   double x = Double.parseDouble(xval.getText().toString());
                   double y = Double.parseDouble(yval.getText().toString());
                    Log.d(TAG, "onClick: Adding a new points. (x,y): (" + x + "," + y + ")");
                    xyValuesArray.add(new XYValues(x, y));
                    init();
                } else {
                    Toast.makeText(Graph.this, "You must fill out both fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (xyValuesArray.size() != 0) {
            createScatterPlot();
        } else {
            Log.d(TAG, "onCreate: No data to plot!");
        }

    }

    private void createScatterPlot() {
        Log.d(TAG, "createScatterPlot: Creating scatter plot");

        xyValuesArray = sortArray(xyValuesArray);

        for (int i = 0; i < xyValuesArray.size(); i++) {

            try{
                double x = xyValuesArray.get(i).getX();
                double y = xyValuesArray.get(i).getY();
                xySeries.appendData(new DataPoint(x,y),true, 1000);
            }catch(IllegalArgumentException e){
                Log.d(TAG, "createScatterPlot: IllegalArgumentException "+ e.getMessage());
            }

        }
        xySeries.setShape(PointsGraphSeries.Shape.TRIANGLE);
        xySeries.setColor(Color.RED);
        xySeries.setSize(20f);

        mScatterPlot.getViewport().setScalable(true);
        mScatterPlot.getViewport().setScalableY(true);
        mScatterPlot.getViewport().setScrollable(true);
        mScatterPlot.getViewport().setScrollableY(true);

        mScatterPlot.getViewport().setYAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxY(500);
        mScatterPlot.getViewport().setMinY(0);

        mScatterPlot.getViewport().setXAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxX(500);
        mScatterPlot.getViewport().setMinX(0);

        mScatterPlot.addSeries(xySeries);

    }

    private ArrayList<XYValues> sortArray(ArrayList<XYValues> array) {

        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(array.size(), 2))));
        int m = array.size() - 1;
        int count = 0;
        Log.d(TAG, "sortArray: Sorting the XYArray");

        while (true) {
            m--;
            if (m <= 0) {
                m = array.size() - 1;
            }
            Log.d(TAG, "sortArray: m = " + m);
            try {
                double tempY = array.get(m - 1).getY();
                double tempX = array.get(m - 1).getX();
                if (tempX > array.get(m).getX()) {
                    array.get(m - 1).setY(array.get(m).getY());
                    array.get(m).setY(tempY);
                    array.get(m - 1).setY(array.get(m).getX());
                    array.get(m).setY(tempX);
                } else if (tempX == array.get(m).getX()) {
                    count++;
                    Log.d(TAG, "sortArray: Count = " + count);
                } else if (array.get(m).getX() > array.get(m - 1).getX()) {
                    count++;
                    Log.d(TAG, "sortArray: Count = " + count);
                }
                if (count == factor) {
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e(TAG, "sortArray: ArrayIndexOutOfBoundException . Need more than one data to create plot" +
                        e.getMessage());
                break;
            }
        }
            return array;
        }



}
