package com.apps.harsh.zest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.harsh.zest.model.Match;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {

    private List<Match> matchList;
    private Context context;
    private FirebaseFirestore firestoreDB;
    private LinearLayout layoutDateTime, layoutDesc;

    public MatchListAdapter(List<Match> matchList, Context context, FirebaseFirestore firestoreDB) {
        this.matchList = matchList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @NonNull
    @Override
    public MatchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);

        return new MatchListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchListAdapter.ViewHolder holder, final int position) {
        final Match match = matchList.get(position);
        Bundle bundle = new Bundle();
        String id = match.getId();
        String gt = match.getGameType();
        String mt = match.getMatchType();
        String dt = match.getDate();
        String tm = match.getTime();
        String pl = match.getPlace();
        String t1 = match.getTeam1();
        String t2 = match.getTeam2();
        String s1 = match.getScore1();
        String s2 = match.getScore2();
        String st = match.getStatus();
        bundle.putString("id", id);
        bundle.putString("gametype", gt);
        bundle.putString("matchtype", mt);
        bundle.putString("date", dt);
        bundle.putString("time", tm);
        bundle.putString("place", pl);
        bundle.putString("team1", t1);
        bundle.putString("team2", t2);
        //bundle.putString("score1", s1);
        //bundle.putString("score2", s2);
        //bundle.putString("status", st);
        holder.gameType.setText(match.getGameType());
        holder.matchType.setText(match.getMatchType());
        holder.date.setText(match.getDate());
        holder.time.setText(match.getTime());
        holder.place.setText(match.getPlace());
        holder.team1.setText(match.getTeam1());
        holder.team2.setText(match.getTeam2());
        holder.score1.setText(match.getScore1());
        holder.score2.setText(match.getScore2());
        holder.status.setText(match.getStatus());
        holder.desc.setText(match.getDesc());

        if(match.getStatus().equals("Match Completed") || match.getStatus().equals("Match Running"))
            layoutDateTime.setVisibility(View.GONE);
        else
            layoutDesc.setVisibility(View.GONE);
    }



    @Override
    public int getItemCount() {
        return matchList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView matchType, gameType, team1, team2, place, time, date, score1, score2, status, desc;
        View mView;


        ViewHolder(View view) {
            super(view);
            mView = view;
            matchType = view.findViewById(R.id.match_type);
            gameType = view.findViewById(R.id.game_type);
            team1 = view.findViewById(R.id.team1);
            team2 = view.findViewById(R.id.team2);
            place = view.findViewById(R.id.place);
            time = view.findViewById(R.id.time);
            date = view.findViewById(R.id.date);
            score1 = view.findViewById(R.id.score1);
            score2 = view.findViewById(R.id.score2);
            status = view.findViewById(R.id.status);
            desc = view.findViewById(R.id.desc);
            layoutDesc = view.findViewById(R.id.descLL);
            layoutDateTime = view.findViewById(R.id.dateTimeLL);
        }
    }
}