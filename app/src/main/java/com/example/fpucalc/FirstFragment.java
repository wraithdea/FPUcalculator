package com.example.fpucalc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;


import java.security.spec.ECField;

public class FirstFragment extends Fragment {

    private TextView carbsTW, proteinsTW, fatsTW, carbsEqResultTW, ratioResultTW, absorbTimeResultTW;
    private Button calculateBT, clearBT;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);

        carbsTW = fragmentFirstLayout.findViewById(R.id.carbs_value_NI);
        proteinsTW = fragmentFirstLayout.findViewById(R.id.proteins_value_NI);
        fatsTW = fragmentFirstLayout.findViewById(R.id.fats_value_NI);
        carbsEqResultTW = fragmentFirstLayout.findViewById(R.id.PF_carbs_equi_result_TW);
        ratioResultTW = fragmentFirstLayout.findViewById(R.id.ratio_result_TW);
        absorbTimeResultTW = fragmentFirstLayout.findViewById(R.id.absorb_time_result_TW);
        calculateBT = fragmentFirstLayout.findViewById(R.id.calculate_BT);
        clearBT = fragmentFirstLayout.findViewById(R.id.clear_BT);

        return fragmentFirstLayout;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calculateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                calculate(view);
            }
        });

        clearBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_values(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void calculate(View view) {
        carbsEqResultTW.setText(String.valueOf(getPFCarbsEquivalent()) + " g");
        ratioResultTW.setText(getCarbsToFPURatio());
        absorbTimeResultTW.setText(String.valueOf(getAbsorptionTime()) + " h");
    }

    private void clear_values(View view) {
        carbsTW.setText("");
        proteinsTW.setText("");
        fatsTW.setText("");
    }

    private int getCarbsValue() {
        String carbsTWVal = carbsTW.getText().toString();
        if (carbsTWVal == null || carbsTWVal.equals("")) {
            carbsTW.setText("0");
            carbsTWVal = carbsTW.getText().toString();
        }
        return Integer.parseInt(carbsTWVal);
    }

    private int getProteinsVal() {
        String proteinsTWVal = proteinsTW.getText().toString();
        if (proteinsTWVal == null || proteinsTWVal.equals("")) {
            proteinsTW.setText("0");
            proteinsTWVal = proteinsTW.getText().toString();
        }
        return Integer.parseInt(proteinsTWVal);
    }

    private int getFatsVal() {
        String fatsTWVal = fatsTW.getText().toString();
        if (fatsTWVal == null || fatsTWVal.equals("")) {
            fatsTW.setText("0");
            fatsTWVal = fatsTW.getText().toString();
        }
        return Integer.parseInt(fatsTWVal);
    }

    private int getPFCarbsEquivalent() {
        int proteinsKcal = getProteinsVal() * 4,
                fatsKcal = getFatsVal() * 9;
        return (proteinsKcal + fatsKcal) / 10;
    }

    private String getCarbsToFPURatio() {
        double carbsVal = (double) getCarbsValue(),
                FPUEq = (double) getPFCarbsEquivalent();
        double sum = carbsVal + FPUEq;
        double carbsRatio = Math.round(carbsVal / sum * 100.0),
        FPURatio = Math.round(FPUEq / sum * 100.0);
        return (int) carbsRatio + ":" + (int) FPURatio;
    }

    private double getAbsorptionTime() {
        return (double) getPFCarbsEquivalent() / 10.0 + 2.0;
    }

    private void hideKeyboard() {
        FragmentActivity activity = getActivity();
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    // TODO preklady

}