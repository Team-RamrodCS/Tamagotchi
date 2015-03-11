package cis.ramrodcs.tamagotchi;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import cis.ramrodcs.tamagotchi.api.Stat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PetStatViewer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PetStatViewer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetStatViewer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Timer timer;
    TimerTask timerTask;
    static long FIXED_DELAY = 1000;
    static long PERIOD = 10000;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PetStatViewer.
     */
    // TODO: Rename and change types and number of parameters
    public static PetStatViewer newInstance(String param1, String param2) {
        PetStatViewer fragment = new PetStatViewer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PetStatViewer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        startTimer();
    }
    public void initializeTimerTask() {
        // Show toast for testing
        int duration = Toast.LENGTH_SHORT;
        CharSequence seq = "Hello from Timer";
        final Toast toast = Toast.makeText(getActivity(),seq,duration);

        // Set a new TimerTask
        timerTask = new TimerTask() {
            public void run() {
                toast.show();
                // TODO: Add update function for the timerTask
                ProgressBar pb2 = (ProgressBar) getView().findViewById(R.id.hungerBar);
                if(pb2 != null) {
                    pb2.setProgress((int) Game.getInstance().getPet().getStat(Stat.HUNGER)*100);
                } else {
                    toast.show();
                }
                /*
                ProgressBar happiness = (ProgressBar) getView().findViewById(R.id.happinessBar);
                ProgressBar hunger = (ProgressBar) getView().findViewById(R.id.hungerBar);
                ProgressBar hygiene = (ProgressBar) getView().findViewById(R.id.hygieneBar);
                ProgressBar energy = (ProgressBar) getView().findViewById(R.id.energyBar);
                System.out.println(happiness);
                happiness.setProgress((int) Game.getInstance().getPet().getStat(Stat.HAPPINESS)*100);
                hunger.setProgress((int) Game.getInstance().getPet().getStat(Stat.HUNGER)*100);
                hygiene.setProgress((int) Game.getInstance().getPet().getStat(Stat.HYGIENE)*100);
                energy.setProgress((int) Game.getInstance().getPet().getStat(Stat.ENERGY)*100);*/
            }
        };
    }

    public void startTimer() {
        // Set a new Timer
        timer = new Timer();

        // Initialize timerTask
        initializeTimerTask();

        // Schedule timer; After first FIXED_DELAYms, the task will run every PERIODms
        timer.schedule(timerTask, FIXED_DELAY, PERIOD);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CharSequence cs = "Test!";
        Toast.makeText(getActivity(), cs, Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_pet_stat_viewer, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
