package com.apps.harsh.zest.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.harsh.zest.ConnectionDetector;
import com.apps.harsh.zest.PageAdapter;
import com.apps.harsh.zest.R;
import com.apps.harsh.zest.model.Match;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.rd.PageIndicatorView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private ViewPager mPager;
    private OnFragmentInteractionListener listener;
    AlertDialog alertDialog;
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    ArrayList<String> place = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> matches = new ArrayList<>();
    ArrayList<String> game = new ArrayList<>();
    ArrayList<String> team1 = new ArrayList<>();
    ArrayList<String> team2 = new ArrayList<>();
    ArrayList<String> score1 = new ArrayList<>();
    ArrayList<String> score2 = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    PageIndicatorView indicator;
    LinearLayout match_data;
    ConnectionDetector connectionDetector;
    CardView c1, c3;
    TextView tv;
    AVLoadingIndicatorView avLoadingIndicatorView;
    private static final String TAG = "Focused";

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        match_data = view.findViewById(R.id.focused_match);
        c1 = view.findViewById(R.id.c1);
        c3 = view.findViewById(R.id.c3);
        tv = view.findViewById(R.id.textViewSP);
        c1.setVisibility(View.INVISIBLE);
        c3.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        avLoadingIndicatorView = view.findViewById(R.id.avi);
        avLoadingIndicatorView.show();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mPager = view.findViewById(R.id.pager);
        indicator = view.findViewById(R.id.indicator);
        FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Please Update");
        alertDialog.setMessage("Latest version available..! \n Please update now");
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent launchIntent = Objects.requireNonNull(getActivity()).getPackageManager().getLaunchIntentForPackage("com.android.vending");
                        dialog.dismiss();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.coep.unofficial.zest18")));
                        getActivity().finish();
                    }
                });
        connectionDetector = new ConnectionDetector(getActivity());
        try {
            if (connectionDetector.isConnected()) {
                date.clear();
                time.clear();
                place.clear();
                status.clear();
                game.clear();
                matches.clear();
                team1.clear();
                team2.clear();
                score1.clear();
                score2.clear();
                desc.clear();
                firestoreDB.collection("Focused")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int count = 0;
                                    for (DocumentSnapshot doc : task.getResult()) {
                                        //Match match = doc.toObject(Match.class);
                                        String id, gt, mt, t1, t2, dt, ti, pl, s1, s2, st, ds;
                                        gt = doc.getString("GameType");
                                        mt = doc.getString("MatchType");
                                        t1 = doc.getString("Team1");
                                        t2 = doc.getString("Team2");
                                        dt = doc.getString("Date");
                                        ti = doc.getString("Time");
                                        pl = doc.getString("Place");
                                        s1 = doc.getString("Score1");
                                        s2 = doc.getString("Score2");
                                        st = doc.getString("Status");
                                        ds = doc.getString("Description");
                                        Match match = new Match(gt, mt, t1, t2, dt, ti, pl, s1, s2, st, ds);
                                        count++;
                                        game.add(match.getGameType());
                                        matches.add(match.getMatchType());
                                        team1.add(match.getTeam1());
                                        team2.add(match.getTeam2());
                                        score1.add(match.getScore1());
                                        score2.add(match.getScore2());
                                        status.add(match.getStatus());
                                        date.add(match.getDate());
                                        time.add(match.getTime());
                                        place.add(match.getPlace());
                                        desc.add(match.getDesc());
                                    }
                                    if (count == 0) {
                                        match_data.setVisibility(View.GONE);
                                    } else {
                                        mPager.setAdapter(new PageAdapter(getActivity(), date, game, matches, place, score1, score2, status, team1, team2, time, desc));
                                        indicator.setViewPager(mPager);
                                        mPager.setOffscreenPageLimit(5);
                                        match_data.setVisibility(View.VISIBLE);
                                    }
                                    avLoadingIndicatorView.hide();
                                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    c1.setVisibility(View.VISIBLE);
                                    c3.setVisibility(View.VISIBLE);
                                    tv.setVisibility(View.VISIBLE);
                                } else {
                                    Log.e(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            } else {
                alertDialog.setTitle("No Internet");
                alertDialog.setMessage("Please Connect to Network...!");
                alertDialog.setCancelable(false);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                startActivity(new Intent(Settings.ACTION_SETTINGS));
                            }
                        });
                alertDialog.show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public interface OnFragmentInteractionListener {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        c1 = view.findViewById(R.id.c1);
        c3 = view.findViewById(R.id.c3);

        final ImageView imageView1 = view.findViewById(R.id.image1);
        final ImageView imageView2 = view.findViewById(R.id.image2);
        final TextView tvName1, tvName2, tvDate1, tvDate2;
        tvName1 = view.findViewById(R.id.textViewName1);
        tvName2 = view.findViewById(R.id.textViewName2);
        tvDate1 = view.findViewById(R.id.textViewDate1);
        tvDate2 = view.findViewById(R.id.textViewDate2);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment simpleFragment = DetailFragment.newInstance();
                ViewCompat.setTransitionName(v.findViewById(R.id.image1), "1");
                ViewCompat.setTransitionName(v.findViewById(R.id.textViewName1), "2");
                ViewCompat.setTransitionName(v.findViewById(R.id.textViewDate1), "3");
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(imageView1, "1")
                        .addSharedElement(tvName1, "2")
                        .addSharedElement(tvDate1, "3")
                        .addToBackStack(TAG)
                        .add(R.id.frameLayout, simpleFragment)
                        .commit();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondDetailFrag simpleFragment = SecondDetailFrag.newInstance();
                ViewCompat.setTransitionName(v.findViewById(R.id.image2), "1");
                ViewCompat.setTransitionName(v.findViewById(R.id.textViewName2), "2");
                ViewCompat.setTransitionName(v.findViewById(R.id.textViewDate2), "3");
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(imageView2, "1")
                        .addSharedElement(tvName2, "2")
                        .addSharedElement(tvDate2, "3")
                        .addToBackStack(TAG)
                        .add(R.id.frameLayout, simpleFragment)
                        .commit();
            }
        });
    }
}
