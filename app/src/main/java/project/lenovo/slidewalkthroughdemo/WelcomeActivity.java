package project.lenovo.slidewalkthroughdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private int[] layouts;
    private TextView[] dots;
    private LinearLayout dotsLayouts;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private Button nextbutton, skipbutton;
    private IntroManager introManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        introManager = new IntroManager(this);
        if(!introManager.isFirstTime()){
            introManager.setFirst(false);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

        if(Build.VERSION.SDK_INT>= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        nextbutton = (Button)findViewById(R.id.btn_next);
        skipbutton = (Button)findViewById(R.id.btn_skip);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        dotsLayouts = (LinearLayout)findViewById(R.id.layoutDots);

        //layouts of welcome sliders
        layouts = new int[]{
                R.layout.screen1,
                R.layout.screen2,
                R.layout.screen3,
                R.layout.screen4,
                R.layout.screen5,
                R.layout.screen6};

        //adding bottom dots
        addButtonDots(0);

        //change status color bar
        changeStatusColor();

        pagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if(current< layouts.length){
                    viewPager.setCurrentItem(current);
                }
                else {
                    Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    private void addButtonDots(int currentPage){
        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInActive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayouts.removeAllViews();

        for(int i = 0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInActive[currentPage]);
            dotsLayouts.addView(dots[i]);
        }
        if(dots.length>0){
            dots[currentPage].setTextColor(colorActive[currentPage]);
        }
    }

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addButtonDots(position);
            if(position == layouts.length-1){
                nextbutton.setText("पृष्ठमा जानुहोस ");
                skipbutton.setVisibility(View.GONE);
            }
            else {
                //still pages are left
                nextbutton.setText(getString(R.string.nextnepali));
                skipbutton.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private  void changeStatusColor(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }

    public class ViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;

        public ViewPagerAdapter(){
        }

        public Object instantiateItem (ViewGroup container, int position){
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container,false);
            container.addView(view);
            return  view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View)object;
            container.removeView(v);
        }
    }
    }


