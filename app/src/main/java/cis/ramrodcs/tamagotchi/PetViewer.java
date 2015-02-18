package cis.ramrodcs.tamagotchi;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cis.ramrodcs.tamagotchi.api.IPetViewer;


public class PetViewer extends ActionBarActivity implements IPetViewer,GestureDetector.OnGestureListener {

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_viewer);

        mDetector = new GestureDetectorCompat(this, this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(velocityX > 0) {
            Intent intent = new Intent(this, MenuViewer.class);
            startActivity(intent);
        } else if (velocityX < 0) {
            Intent intent = new Intent(this, PetStatViewer.class);
            startActivity(intent);
        }
        return false;
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
}
