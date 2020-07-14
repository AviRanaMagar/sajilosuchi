package project.lenovo.slidewalkthroughdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LEVONO on 5/11/2017.
 */

public class IntroManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirst";

    public IntroManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirst(Boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTime(){
        return  pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
