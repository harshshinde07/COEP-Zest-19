package com.apps.harsh.zest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PageAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> date;
    private ArrayList<String> team1;
    private ArrayList<String> team2;
    private ArrayList<String> gameType;
    private ArrayList<String> status;
    private ArrayList<String> matchType;
    private ArrayList<String> time;
    private ArrayList<String> place;
    private ArrayList<String> score1;
    private ArrayList<String> score2;
    private ArrayList<String> desc;
    private LinearLayout layoutDateTime, layoutDesc;

    public PageAdapter(){}

    public PageAdapter(Context context, ArrayList<String> date, ArrayList<String> gameType, ArrayList<String> matchType, ArrayList<String> place, ArrayList<String> score1, ArrayList<String> score2, ArrayList<String> status, ArrayList<String> team1, ArrayList<String> team2, ArrayList<String> time, ArrayList<String> desc) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.team1 = team1;
        this.team2 = team2;
        this.gameType = gameType;
        this.status = status;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.matchType = matchType;
        this.desc = desc;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if(team1.size() > 5)
        return 5;
        else
            return team1.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup matchView, int position) {
        TextView tvmatchType, tvgameType, tvteam1, tvteam2, tvplace, tvtime, tvdate, tvstatus, tvscore1, tvscore2, tvdesc;

        View view = inflater.inflate(R.layout.item_match, matchView, false);
        tvmatchType = view.findViewById(R.id.match_type);
        tvgameType = view.findViewById(R.id.game_type);
        tvteam1 = view.findViewById(R.id.team1);
        tvteam2 = view.findViewById(R.id.team2);
        tvplace = view.findViewById(R.id.place);
        tvtime = view.findViewById(R.id.time);
        tvdate = view.findViewById(R.id.date);
        tvscore1 = view.findViewById(R.id.score1);
        tvscore2 = view.findViewById(R.id.score2);
        tvstatus = view.findViewById(R.id.status);
        tvdesc = view.findViewById(R.id.desc);

        layoutDesc = view.findViewById(R.id.descLL);
        layoutDateTime = view.findViewById(R.id.dateTimeLL);

        tvteam1.setText(team1.get(position));
        tvteam2.setText(team2.get(position));
        tvmatchType.setText(matchType.get(position));
        tvgameType.setText(gameType.get(position));
        tvplace.setText(place.get(position));
        tvtime.setText(time.get(position));
        tvdate.setText(date.get(position));
        tvstatus.setText(status.get(position));
        tvscore1.setText(score1.get(position));
        tvscore2.setText(score2.get(position));
        tvdesc.setText(desc.get(position));

        if(tvstatus.getText().equals("Match Completed"))
            layoutDateTime.setVisibility(View.GONE);
        else
            layoutDesc.setVisibility(View.GONE);

        matchView.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}