package com.apps.harsh.zest.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apps.harsh.zest.ConnectionDetector;
import com.apps.harsh.zest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{
    Bundle bundle;
    ConnectionDetector connectionDetector;
    private OnFragmentInteractionListener listener;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        connectionDetector = new ConnectionDetector(getActivity());
        LinearLayout hockey = view.findViewById(R.id.hockey);
        LinearLayout kabbadi = view.findViewById(R.id.kabbadi);
        LinearLayout kho_kho = view.findViewById(R.id.kho_kho);
        LinearLayout football = view.findViewById(R.id.football);
        LinearLayout handball = view.findViewById(R.id.handball);
        LinearLayout basketball = view.findViewById(R.id.basketball);
        LinearLayout volleyball = view.findViewById(R.id.volleyball);
        LinearLayout badminton = view.findViewById(R.id.badminton);
        LinearLayout lawntennis = view.findViewById(R.id.lawntennis);
        LinearLayout tabletennis = view.findViewById(R.id.tabletennis);
        LinearLayout ballbadminton = view.findViewById(R.id.ballbadminton);
        LinearLayout baseball = view.findViewById(R.id.baseball);
        LinearLayout cricket = view.findViewById(R.id.cricket);
        LinearLayout boxcricket = view.findViewById(R.id.boxcricket);
        LinearLayout mixcricket = view.findViewById(R.id.mixcricket);
        hockey.setOnClickListener(this);
        kabbadi.setOnClickListener(this);
        hockey.setOnClickListener(this);
        kho_kho.setOnClickListener(this);
        football.setOnClickListener(this);
        handball.setOnClickListener(this);
        baseball.setOnClickListener(this);
        basketball.setOnClickListener(this);
        volleyball.setOnClickListener(this);
        badminton.setOnClickListener(this);
        lawntennis.setOnClickListener(this);
        tabletennis.setOnClickListener(this);
        ballbadminton.setOnClickListener(this);
        cricket.setOnClickListener(this);
        boxcricket.setOnClickListener(this);
        mixcricket.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), TableFragment.class);

        bundle = new Bundle();
        switch (v.getId()){
            case R.id.hockey:
                bundle.putString("Title",getString(R.string.hockey));
                break;
            case R.id.kabbadi:
                bundle.putString("Title",getString(R.string.kabbadi));
                break;
            case R.id.kho_kho:
                bundle.putString("Title",getString(R.string.kho_kho));
                break;
            case R.id.football:
                bundle.putString("Title",getString(R.string.football));
                break;
            case R.id.handball:
                bundle.putString("Title",getString(R.string.handball));
                break;
            case R.id.basketball:
                bundle.putString("Title",getString(R.string.basket_ball));
                break;
            case R.id.volleyball:
                bundle.putString("Title",getString(R.string.volley_ball));
                break;
            case R.id.badminton:
                bundle.putString("Title",getString(R.string.badminton));
                break;
            case R.id.lawntennis:
                bundle.putString("Title",getString(R.string.lawn_tennis));
                break;
            case R.id.tabletennis:
                bundle.putString("Title",getString(R.string.table_tennis));
                break;
            case R.id.ballbadminton:
                bundle.putString("Title",getString(R.string.ball_badminton));
                break;
            case R.id.baseball:
                bundle.putString("Title",getString(R.string.base_ball));
                break;
            case R.id.cricket:
                bundle.putString("Title",getString(R.string.cricket));
                break;
            case R.id.boxcricket:
                bundle.putString("Title",getString(R.string.box_cricket));
                break;
            case R.id.mixcricket:
                bundle.putString("Title",getString(R.string.mix_cricket));
                break;
            default:
                break;
        }

        intent.putExtras(bundle);
        startActivity(intent);

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

    public interface OnFragmentInteractionListener {
    }
}