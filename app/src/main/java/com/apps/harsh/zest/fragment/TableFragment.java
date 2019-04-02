package com.apps.harsh.zest.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.apps.harsh.zest.ConnectionDetector;
import com.apps.harsh.zest.EmptyRecyclerView;
import com.apps.harsh.zest.MatchListAdapter;
import com.apps.harsh.zest.R;
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

public class TableFragment extends AppCompatActivity {
    AVLoadingIndicatorView avLoadingIndicatorView;
    EmptyRecyclerView recyclerView;
    private MatchListAdapter mAdapter;
    private FirebaseFirestore firestoreDB;
    List<Match> matchList = new ArrayList<>();
    CollectionReference scheduleRef;
    AlertDialog alertDialog;
    Bundle bundle;
    ConnectionDetector connectionDetector;
    static String collection = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_table);
        connectionDetector = new ConnectionDetector(this);
        avLoadingIndicatorView = findViewById(R.id.avi);
        recyclerView = findViewById(R.id.table_list);
        avLoadingIndicatorView.show();
        firestoreDB = FirebaseFirestore.getInstance();
        mAdapter = new MatchListAdapter(matchList, this, firestoreDB);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        View emptyView = findViewById(R.id.emptyView);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        alertDialog = new AlertDialog.Builder(this).create();
        bundle = getIntent().getExtras();
        if (bundle != null) {
            collection = bundle.getString("Collection");
            //Toast.makeText(this, collection, Toast.LENGTH_SHORT).show();
            if (connectionDetector.isConnected()) {
                if (bundle != null) {
                    switch (bundle.getString("Title")) {

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
        }
    }

    public void loadMatchList(final String name1, final String name2) {
        scheduleRef = firestoreDB.collection("Schedule");

        Query firstQuery = scheduleRef.whereEqualTo("GameType", name1);
        Query secondQuery = scheduleRef.whereEqualTo("GameType", name2);

        Task firstTask = firstQuery.get();
        Task secondTask = secondQuery.get();
        Task<List<QuerySnapshot>> allTasks = Tasks.whenAllSuccess(firstTask, secondTask);
        allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
            @Override
            public void onSuccess(List<QuerySnapshot> querySnapshots) {
                for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String id, gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc;
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
                        desc = doc.getString("Description");
                        Match match = new Match(gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc);
                        match.setId(doc.getId());
                        matchList.add(match);
                    }
                }
                mAdapter.notifyDataSetChanged();
                avLoadingIndicatorView.hide();
            }
        });
    }

    public void loadMatchList(String name1) {
        scheduleRef = firestoreDB.collection("Schedule");
        scheduleRef.whereEqualTo("GameType", name1)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            String id, gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc;
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
                            desc = doc.getString("Description"); //TODO calculate actual description depending on other values

                            Match match = new Match(gameType, matchType, team1, team2, date, time, place, score1, score2, status, desc);
                            match.setId(doc.getId());
                            matchList.add(match);
                        }
                        mAdapter.notifyDataSetChanged();
                        avLoadingIndicatorView.hide();
                    }
                });
    }
}
