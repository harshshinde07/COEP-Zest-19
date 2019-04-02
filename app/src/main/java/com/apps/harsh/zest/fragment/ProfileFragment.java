package com.apps.harsh.zest.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apps.harsh.zest.ConnectionDetector;
import com.apps.harsh.zest.R;
import com.github.jinatonic.confetti.CommonConfetti;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    private int[] yData = new int[5];
    private String[] xData = new String[5];
    PieChart pieChart;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ConnectionDetector connectionDetector;
    AlertDialog alertDialog;
    private OnFragmentInteractionListener listener;
    String c1, c2, c3, c4, c5, p1, p2, p3, p4, p5;
    int first, second, third, fourth, fifth;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        connectionDetector = new ConnectionDetector(getActivity());
        alertDialog = new AlertDialog.Builder(getActivity()).create();

        pieChart = view.findViewById(R.id.piechart);
        pieChart.setRotationEnabled(true);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(18f);
        pieChart.setCenterText("Zest 19 Champions");
        pieChart.getDescription().setText("");
        pieChart.getDescription().setTextSize(25f);
        pieChart.getDescription().setXOffset(50);
        pieChart.getDescription().setYOffset(50);
        pieChart.animateXY(1000, 1000);
        pieChart.setCenterTextSize(15);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setDrawEntryLabels(true);

        if (connectionDetector.isConnected()) {
            ProfileFragment profileFragment = new ProfileFragment();
            avLoadingIndicatorView = view.findViewById(R.id.avi);
            avLoadingIndicatorView.show();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference collectionReference = db.collection("Championship");
            collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                    if (queryDocumentSnapshots != null) {
                        if (queryDocumentSnapshots.size() != 0) {
                            c1 = queryDocumentSnapshots.getDocuments().get(0).getString("College 1");
                            c2 = queryDocumentSnapshots.getDocuments().get(0).getString("College 2");
                            c3 = queryDocumentSnapshots.getDocuments().get(0).getString("College 3");
                            c4 = queryDocumentSnapshots.getDocuments().get(0).getString("College 4");
                            c5 = queryDocumentSnapshots.getDocuments().get(0).getString("College 5");
                            p1 = queryDocumentSnapshots.getDocuments().get(0).getString("Point 1");
                            p2 = queryDocumentSnapshots.getDocuments().get(0).getString("Point 2");
                            p3 = queryDocumentSnapshots.getDocuments().get(0).getString("Point 3");
                            p4 = queryDocumentSnapshots.getDocuments().get(0).getString("Point 4");
                            p5 = queryDocumentSnapshots.getDocuments().get(0).getString("Point 5");
                            xData[0] = c1.toUpperCase();
                            xData[1] = c2.toUpperCase();
                            xData[2] = c3.toUpperCase();
                            xData[3] = c4.toUpperCase();
                            xData[4] = c5.toUpperCase();

                            try {
                                first = Integer.parseInt(p1);
                                second = Integer.parseInt(p2);
                                third = Integer.parseInt(p3);
                                fourth = Integer.parseInt(p4);
                                fifth = Integer.parseInt(p5);
                            } catch (NumberFormatException xe) {
                                Toast.makeText(getContext(), xe.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            yData[0] = first;
                            yData[1] = second;
                            yData[2] = third;
                            yData[3] = fourth;
                            yData[4] = fifth;
                            addDataset(pieChart);
                            CommonConfetti.rainingConfetti(container, new int[]{Color.BLACK, Color.BLUE, Color.RED, Color.CYAN, Color.YELLOW})
                                    .stream(5000);
                        } else {
                            avLoadingIndicatorView.hide();
                        }
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

        return view;
    }

    private void addDataset(PieChart pieChart) {
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        for (int i = 0; i < yData.length; i++) {
            yEntry.add(new PieEntry(yData[i], xData[i], i));
        }

        PieDataSet pieDataSet = new PieDataSet(yEntry, "");
        pieDataSet.setSliceSpace(5);
        pieDataSet.setValueTextSize(15);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0, 128, 255));
        colors.add(Color.rgb(230, 0, 115));
        colors.add(Color.rgb(0, 204, 0));
        colors.add(Color.rgb(255, 102, 26));
        colors.add(Color.rgb(102, 0, 102));
        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        avLoadingIndicatorView.hide();

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
