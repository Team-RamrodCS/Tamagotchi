package cis.ramrodcs.tomagotcha.virtualpenguin;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import cis.ramrodcs.tamagotchi.api.IPet;
import cis.ramrodcs.tamagotchi.api.Stat;


public class PetViewer extends ActionBarActivity {

    Timer timer;
    TimerTask timerTask;
    static long FIXED_DELAY = 100;
    static long PERIOD = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_viewer);

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

        // Initialize timerTask// Show toast for testing
        int duration = Toast.LENGTH_SHORT;
        CharSequence seq = "Your pet has died. You are a terrible owner.";
        final Toast toast = Toast.makeText(this,seq,duration);

        // Set a new TimerTask
        timerTask = new TimerTask() {
            public void run() {
                // TODO: Add update function for the timerTask
                Game.getInstance().getPet().update();
                if(Game.getInstance().getPet().getWellness() <= .15 && Math.random() > .5) {
                    toast.show();
                    Game.getInstance().setPet(Game.getInstance().createPet());
                }
                renderBars();
            }
        };

        // Schedule timer; After first FIXED_DELAYms, the task will run every PERIODms
        timer.schedule(timerTask, FIXED_DELAY, PERIOD);
    }

    public void renderBars() {
        ProgressBar wellnessBar = (ProgressBar) findViewById(R.id.wellnessBar);
        ProgressBar hungerBar = (ProgressBar) findViewById(R.id.hungerBar);
        ProgressBar hygieneBar = (ProgressBar) findViewById(R.id.hygieneBar);
        ProgressBar energyBar = (ProgressBar) findViewById(R.id.energyBar);
        ProgressBar happinessBar = (ProgressBar) findViewById(R.id.happinessBar);

        wellnessBar.setProgress((int) (Game.getInstance().getPet().getWellness() * 100));
        hungerBar.setProgress((int) (Game.getInstance().getPet().getStat(Stat.HUNGER) * 100));
        hygieneBar.setProgress((int) (Game.getInstance().getPet().getStat(Stat.HYGIENE) * 100));
        energyBar.setProgress((int) (Game.getInstance().getPet().getStat(Stat.ENERGY) * 100));
        happinessBar.setProgress((int) (Game.getInstance().getPet().getStat(Stat.HAPPINESS) * 100));

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
    public void feed(View view) {
        IPet pet = Game.getInstance().getPet();
        if(pet.isSleeping()) {
            Toast.makeText(this, "Shhh... Your pet is sleeping!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pet.canModifyStat(Stat.HUNGER, .2)) {
            Toast.makeText(this, "Your pet is not hungry!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pet.canModifyStat(Stat.ENERGY, -.05)) {
            Toast.makeText(this, "Your pet is too tired to eat!", Toast.LENGTH_SHORT).show();
            return;
        }
        pet.modifyStat(Stat.HUNGER, .2);
        pet.modifyStat(Stat.ENERGY, -.05);
        renderBars();
    }
    public void clean(View view) {
        IPet pet = Game.getInstance().getPet();
        if(pet.isSleeping()) {
            Toast.makeText(this, "Shhh... Your pet is sleeping!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pet.canModifyStat(Stat.HYGIENE, .3)) {
            Toast.makeText(this, "Your pet is not dirty!", Toast.LENGTH_SHORT).show();
            return;
        }
        pet.modifyStat(Stat.HYGIENE, .3);
        renderBars();
    }
    public void play(View view) {
        IPet pet = Game.getInstance().getPet();
        if(pet.isSleeping()) {
            Toast.makeText(this, "Shhh... Your pet is sleeping!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pet.canModifyStat(Stat.HUNGER, -.1)) {
            Toast.makeText(this, "Your pet is too hungry to play!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pet.canModifyStat(Stat.ENERGY, -.1)) {
            Toast.makeText(this, "Your pet is too tired to play!", Toast.LENGTH_SHORT).show();
            return;
        }
        pet.modifyStat(Stat.HUNGER, -.1);
        pet.modifyStat(Stat.HYGIENE, -.1);
        pet.modifyStat(Stat.HAPPINESS, .3);
        pet.modifyStat(Stat.ENERGY, -.1);
        renderBars();
    }
}
