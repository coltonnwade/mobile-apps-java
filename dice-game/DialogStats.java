package com.example.wade_colton_assignment10;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogStats extends DialogFragment {

    public DialogStats() {

    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        // DialogBuilder
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        // LayoutInflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Creates View
        View dialogView = inflater.inflate(R.layout.stats, null);
        builder.setView(dialogView).setMessage("About");

        // Gets MainActivity
        MainActivity callingActivity = (MainActivity) getActivity();



        // sets TextViews/Buttons
        final TextView rolls = dialogView.findViewById(R.id.txtRolls);
        final TextView doubles = dialogView.findViewById(R.id.txtDoubles);
        final TextView triples = dialogView.findViewById(R.id.txtTriples);
        final Button cancel = dialogView.findViewById(R.id.btnCancel);
        final Button reset = dialogView.findViewById(R.id.resetBtn);

        // setts text for TextView's
        rolls.setText(String.format("Rolls: %s", callingActivity.dice.getRolls()));
        doubles.setText(String.format("Doubles: %s", callingActivity.dice.getDoubles()));
        triples.setText(String.format("Triples: %s", callingActivity.dice.getTriples()));

        // When Cancel is Clicked it dismisses Dialog
        cancel.setOnClickListener(view -> dismiss());



        // When reset is Clicked it resets stats
        reset.setOnClickListener(view -> {
            callingActivity.resetGame();
            dismiss();
        });


        return builder.create();
    }
}

