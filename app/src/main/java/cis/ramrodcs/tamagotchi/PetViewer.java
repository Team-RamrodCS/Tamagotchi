package cis.ramrodcs.tamagotchi;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PetViewer extends ActionBarActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat mDetector;
    int currentView = 0;

    Timer timer;
    TimerTask timerTask;
    static long FIXED_DELAY = 1000;
    static long PERIOD = 10000;

    //ProgressBar mybar = (ProgressBar) findViewById(R.id.progressBar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_viewer);

        mDetector = new GestureDetectorCompat(this, this);

        // Start Timer
        startTimer();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }

    public void startTimer() {
        // Set a new Timer
        timer = new Timer();

        // Initialize timerTask
        initializeTimerTask();

        // Schedule timer; After first FIXED_DELAYms, the task will run every PERIODms
        timer.schedule(timerTask, FIXED_DELAY, PERIOD);
    }

    public void initializeTimerTask() {
        // Show toast for testing
        int duration = Toast.LENGTH_SHORT;
        CharSequence seq = "Hello from Timer";
        final Toast toast = Toast.makeText(this,seq,duration);

        // Set a new TimerTask
        timerTask = new TimerTask() {
            public void run() {
                toast.show();
                // TODO: Add update function for the timerTask
                // Game.getInstance().getPet().update();

            }
        };
    }

    public ActionBarActivity getPetView() {
        return this;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            // Option 1

            Toast.makeText(this, "Has focus!", Toast.LENGTH_SHORT).show();
            ImageView penguinImage = (ImageView) findViewById(R.id.penguin_image);

            Calculate(penguinImage);

            /*penguinImage.setBackgroundResource(R.drawable.penguin_animation);
            StateListDrawable background = (StateListDrawable) penguinImage.getBackground();
            Drawable current = background.getCurrent();
            if (current instanceof AnimationDrawable)
            {
                Toast.makeText(this, "Is Drawable!", Toast.LENGTH_SHORT).show();
                AnimationDrawable penguinAni = (AnimationDrawable) current;
                penguinAni.start();
            }
            Toast.makeText(this, "Reached the end!", Toast.LENGTH_SHORT).show();

            // Option 2
            StateListDrawable background = (StateListDrawable) penguinImage.getBackground();
            AnimationDrawable penguinAnimation = (AnimationDrawable) background.getCurrent();
            penguinAnimation.setVisible(true, true);*/

            // Option 3
            penguinImage.setBackgroundResource(R.drawable.penguin_animation);
            AnimationDrawable penguinAnimation = (AnimationDrawable) penguinImage.getBackground();
            penguinAnimation.start();
        }
    }


    @Override
    public void onResume()
    {
        super.onResume();
        Toast.makeText(this, "Has focus!", Toast.LENGTH_SHORT).show();

        ImageView penguinImage = (ImageView) findViewById(R.id.penguin_image);
        penguinImage.setBackgroundResource(R.drawable.penguin_animation);

        AnimationDrawable penguinAnimation = (AnimationDrawable) penguinImage.getBackground();
        penguinAnimation.start();
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pet_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    public void openSettings(View view)
    {
        Toast.makeText(this, "Settings Toast!", Toast.LENGTH_SHORT).show();
    }

    public void quitApp(View view)
    {
        this.finish();
        System.exit(0);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(100);

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int sensitivity = 500;
        if(velocityX > sensitivity) {
            swipeLeft();
        } else if (velocityX < -sensitivity) {
            swipeRight();
        }
        return false;
    }

    private void swipeRight() {
        if(currentView < 0) {
            setContentView(R.layout.fragment_pet_viewer);
            onResume();
        } else if (currentView == 0) {
            setContentView(R.layout.fragment_pet_stat_viewer);
        } else {
            // Already to the right, no further pages.
            return;
        }
        ++currentView;

    }

    private void swipeLeft() {
        if(currentView < 0) {
            // Already to the left, no further pages.
            return;
        } else if (currentView == 0) {
            setContentView(R.layout.fragment_menu_viewer);
        } else {
            setContentView(R.layout.fragment_pet_viewer);
            onResume();
        }
        --currentView;

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pet_viewer, container, false);
            return rootView;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
        /*
        int action = MotionEventCompat.getActionMasked(event);
        String res = "";
        switch( action ) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(this, "down", Toast.LENGTH_SHORT).show();
                return true;
            case MotionEvent.ACTION_UP:
                Toast.makeText(this, "up", Toast.LENGTH_SHORT).show();
                return true;
            case MotionEvent.ACTION_MOVE:
                //Toast.makeText(this, "move", Toast.LENGTH_SHORT).show();
                return true;
            case MotionEvent.ACTION_CANCEL:
                Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
                return true;
            case MotionEvent.ACTION_OUTSIDE:
                Toast.makeText(this, "outside", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onTouchEvent(event);

        }
        */
    }


    public void Calculate(View view)
    {
        if(view.getId()==R.id.button2) {

            Toast.makeText(this, "Calculate!", Toast.LENGTH_SHORT).show();

            Random randomInt = new Random();

            int percent = randomInt.nextInt(100);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            Resources res = getResources();
            Rect bounds = progressBar.getProgressDrawable().getBounds();

            if (percent >=  67)
            {
                progressBar.setProgressDrawable(res.getDrawable(R.drawable.greenprogressbar));
            }
            else if(percent >= 33)
            {
                progressBar.setProgressDrawable(res.getDrawable(R.drawable.yellowprogressbar));
            }
            else {
                progressBar.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));
            }
            progressBar.getProgressDrawable().setBounds(bounds);
            progressBar.setProgress(percent);
        }
    }

}
