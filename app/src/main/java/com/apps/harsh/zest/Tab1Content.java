package com.apps.harsh.zest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apps.harsh.zest.model.Match;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class Tab1Content extends Fragment {
    Bundle bundle;
    static int openCount;
    ConnectionDetector connectionDetector;
    AVLoadingIndicatorView avLoadingIndicatorView;
    AlertDialog alertDialog;
    static String collection = null;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private EmptyRecyclerView recyclerView;
    private MatchListAdapter mAdapter;
    private FirebaseFirestore firestoreDB;
    List<Match> matchList = new ArrayList<>();
    CollectionReference scheduleRef;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1content, container, false);
        avLoadingIndicatorView = rootView.findViewById(R.id.avi);
        avLoadingIndicatorView.show();
        firestoreDB = FirebaseFirestore.getInstance();
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        mAdapter = new MatchListAdapter(matchList, getContext(), firestoreDB);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        View emptyView = rootView.findViewById(R.id.emptyView);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (collection) {

                    case "HOCKEY":
                        loadMatchList("Hockey (Boys)", "Hockey (Girls)");
                        break;

                    case "KABADDI":
                        loadMatchList("Kabaddi (Boys)", "Kabaddi (Girls)");
                        break;
                    case "KHO-KHO":
                        loadMatchList("Kho-Kho (Boys)", "Kho-Kho (Girls)");
                        break;

                    case "FOOTBALL":
                        loadMatchList("Football (Boys)", "Football (Girls)");
                        break;

                    case "HANDBALL":
                        loadMatchList("Handball (Boys)", "Handball (Girls)");
                        break;

                    case "BASKET BALL":
                        loadMatchList("Basketball (Boys)", "Basketball (Girls)");
                        break;

                    case "VOLLEY BALL":
                        loadMatchList("Volleyball (Boys)", "Volleyball (Girls)");
                        break;

                    case "BADMINTON":
                        loadMatchList("Badminton (Boys)", "Badminton (Girls)");
                        break;

                    case "LAWN TENNIS":
                        loadMatchList("Lawn Tennis (Boys)", "Lawn Tennis (Girls)");
                        break;
                    case "TABLE TENNIS":
                        loadMatchList("Table Tennis (Boys)", "Table Tennis (Girls)");
                        break;

                    case "BALL BADMINTON":
                        loadMatchList("Ball- Badminton (Boys)", "Ball- Badminton (Girls)");
                        break;

                    case "BASE BALL":
                        loadMatchList("Baseball (Boys)", "Baseball (Girls)");
                        break;

                    case "CRICKET":
                        loadMatchList("Cricket (Boys)", "Cricket (Girls)");
                        break;

                    case "BOX CRICKET":
                        loadMatchList("Box Cricket");
                        break;

                    case "MIX CRICKET":
                        loadMatchList("Mix Cricket");
                        break;

                    default:
                        break;
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setHasFixedSize(true);
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        connectionDetector = new ConnectionDetector(getActivity());
        bundle = getArguments();
        if (bundle != null) {
            collection = bundle.getString("Collection");
            //Toast.makeText(getContext(), collection + "Tab1", Toast.LENGTH_LONG).show();
            if (connectionDetector.isConnected()) {
                switch (collection) {

                    case "HOCKEY":
                        loadMatchList("Hockey (Boys)", "Hockey (Girls)");
                        break;

                    case "KABADDI":
                        loadMatchList("Kabaddi (Boys)", "Kabaddi (Girls)");
                        break;
                    case "KHO-KHO":
                        loadMatchList("Kho-Kho (Boys)", "Kho-Kho (Girls)");
                        break;

                    case "FOOTBALL":
                        loadMatchList("Football (Boys)", "Football (Girls)");
                        break;

                    case "HANDBALL":
                        loadMatchList("Handball (Boys)", "Handball (Girls)");
                        break;

                    case "BASKET BALL":
                        loadMatchList("Basketball (Boys)", "Basketball (Girls)");
                        break;

                    case "VOLLEY BALL":
                        loadMatchList("Volleyball (Boys)", "Volleyball (Girls)");
                        break;

                    case "BADMINTON":
                        loadMatchList("Badminton (Boys)", "Badminton (Girls)");
                        break;

                    case "LAWN TENNIS":
                        loadMatchList("Lawn Tennis (Boys)", "Lawn Tennis (Girls)");
                        break;
                    case "TABLE TENNIS":
                        loadMatchList("Table Tennis (Boys)", "Table Tennis (Girls)");
                        break;

                    case "BALL BADMINTON":
                        loadMatchList("Ball- Badminton (Boys)", "Ball- Badminton (Girls)");
                        break;

                    case "BASE BALL":
                        loadMatchList("Baseball (Boys)", "Baseball (Girls)");
                        break;

                    case "CRICKET":
                        loadMatchList("Cricket (Boys)", "Cricket (Girls)");
                        break;

                    case "BOX CRICKET":
                        loadMatchList("Box Cricket");
                        break;

                    case "MIX CRICKET":
                        loadMatchList("Mix Cricket");
                        break;

                    default:
                        break;
                }
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
            openCount++;
        }
        return rootView;
    }


    public void loadMatchList(final String name1, final String name2) {
        scheduleRef = firestoreDB.collection("Schedule");
        matchList.clear();
        Query firstQuery = scheduleRef.whereEqualTo("GameType", name1).whereEqualTo("Status", "Match Running");
        Query secondQuery = scheduleRef.whereEqualTo("GameType", name2).whereEqualTo("Status", "Match Running");

        Task firstTask = firstQuery.get();
        Task secondTask = secondQuery.get();
        Task<List<QuerySnapshot>> allTasks = Tasks.whenAllSuccess(firstTask, secondTask);
        allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
            @Override
            public void onSuccess(List<QuerySnapshot> querySnapshots) {
                for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String id, gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc, set, ball, inning, over, run, target, wicket, st1, st2;
                        gameType = doc.getString("GameType");
                        matchType = doc.getString("MatchType");
                        team1 = doc.getString("Team1");
                        team2 = doc.getString("Team2");
                        date = doc.getString("Date");
                        time = doc.getString("Time");
                        place = doc.getString("Place");
                        score1 = doc.getString("Score1");
                        score2 = doc.getString("Score2");
                        status = doc.getString("Status");
                        assert gameType != null;
                        if (gameType.equalsIgnoreCase("Baseball (Boys)") || gameType.equalsIgnoreCase("Baseball (Girls)")) {
                            st1 = doc.getString("Status1");
                            st2 = doc.getString("Status2");
                            desc = team1 + " " + st1 + " and "+ team2 + " " +st2;
                            Match match = new Match(gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc);
                            match.setId(doc.getId());
                            matchList.add(match);
                        } else if (gameType.equalsIgnoreCase("Cricket (Boys)") || gameType.equalsIgnoreCase("Cricket (Girls)") || gameType.equalsIgnoreCase("Box Cricket") || gameType.equalsIgnoreCase("Mix Cricket")) {
                            ball = doc.getString("Ball");
                            inning = doc.getString("Inning");
                            over = doc.getString("Over");
                            run = doc.getString("Run");
                            target = doc.getString("Target");
                            wicket = doc.getString("Wicket");
                            assert score1 != null;
                            if(score1.equals("Bat")) { // First team is batting
                                st1 = run + "/" + wicket;
                                st2 = over + "Overs";
                                desc = team1 + " is batting first.";
                                assert inning != null;
                                if(inning.equals("2")) {
                                    st2 = "Target: " + target;
                                    int o, r, t, b, required, remaining;
                                    o = Integer.parseInt(over);
                                    r = Integer.parseInt(run);
                                    t = Integer.parseInt(target);
                                    b = Integer.parseInt(ball);
                                    required = t - r;
                                    remaining = 120 - (o * 6 + b);
                                    desc = team1 + "requires " + required + " runs in " + remaining + " balls.";
                                }
                            } else {
                                st1 = over + "Overs";
                                st2 = run + "/" + wicket;
                                desc = team2 + " is batting first.";
                                if(inning.equals("2")) {
                                    st1 = "Target: " + target;
                                    int o, r, t, b, required, remaining;
                                    o = Integer.parseInt(over);
                                    r = Integer.parseInt(run);
                                    t = Integer.parseInt(target);
                                    b = Integer.parseInt(ball);
                                    required = t - r;
                                    remaining = 120 - (o * 6 + b);
                                    desc = team1 + "requires " + required + " runs in " + remaining + " balls.";
                                }
                            }
                            Match match = new Match(gameType, matchType, team1, team2, date, time, place, st1, st2, status, desc);
                            match.setId(doc.getId());
                            matchList.add(match);
                        } else if (gameType.equalsIgnoreCase("Badminton (Boys)") || gameType.equalsIgnoreCase("Badminton (Girls)") || gameType.equalsIgnoreCase("Ball- Badminton (Boys)") || gameType.equalsIgnoreCase("Ball- Badminton (Girls)") || gameType.equalsIgnoreCase("Lawn Tennis (Boys)") || gameType.equalsIgnoreCase("Lawn Tennis (Girls)") || gameType.equalsIgnoreCase("Volleyball (Boys)") || gameType.equalsIgnoreCase("Volleyball (Girls)") || gameType.equalsIgnoreCase("Table Tennis (Boys)") || gameType.equalsIgnoreCase("Table Tennis (Girls)")) {
                            set = doc.getString("Set");
                            desc = "Set number + "+ set + " in progress";
                            Match match = new Match(gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc);
                            match.setId(doc.getId());
                            matchList.add(match);
                        } else {
                            desc = doc.getString("Description");
                            Match match = new Match(gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc);
                            match.setId(doc.getId());
                            matchList.add(match);
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                avLoadingIndicatorView.hide();
            }
        });
    }

    public void loadMatchList(String name1) {
        matchList.clear();
        scheduleRef = firestoreDB.collection("Schedule");
        scheduleRef.whereEqualTo("GameType", name1).whereEqualTo("Status", "Match Running")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            String id, gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc, ball, inning, over, run, target, wicket, st1, st2;
                            gameType = doc.getString("GameType");
                            matchType = doc.getString("MatchType");
                            team1 = doc.getString("Team1");
                            team2 = doc.getString("Team2");
                            date = doc.getString("Date");
                            time = doc.getString("Time");
                            place = doc.getString("Place");
                            score1 = doc.getString("Score1"); // batting or bowling Team 1
                            score2 = doc.getString("Score2"); // batting or bowling Team 2
                            status = doc.getString("Status");
                            desc = doc.getString("Result");
                            ball = doc.getString("Ball");
                            inning = doc.getString("Inning");
                            over = doc.getString("Over");
                            run = doc.getString("Run");
                            target = doc.getString("Target");
                            wicket = doc.getString("Wicket");
                            assert score1 != null;
                            if(score1.equals("Bat")) { // First team is batting
                                st1 = run + "/" + wicket;
                                st2 = over + "Overs";
                                assert inning != null;
                                if(inning.equals("2"))
                                    st2 = "Target: " + target;
                            } else {
                                st1 = over + "Overs";
                                st2 = run + "/" + wicket;
                                if(inning.equals("2"))
                                    st1 = "Target: " + target;
                            }
                            Match match = new Match(gameType, matchType, team1, team2, date, time, place, st1, st2, status, desc);
                            match.setId(doc.getId());
                            matchList.add(match);
                        }
                        mAdapter.notifyDataSetChanged();
                        avLoadingIndicatorView.hide();
                    }
                });
    }
}
