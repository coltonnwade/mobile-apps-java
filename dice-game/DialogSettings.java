package com.example.wade_colton_assignment10;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogSettings extends DialogFragment {


    public DialogSettings() {
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        // Dialog Builder
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        // LayoutInflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Creates DialogView
        View dialogView = inflater.inflate(R.layout.settings, null);
        builder.setView(dialogView).setMessage("Settings");


        // Sets CheckBoxes/Buttons
        final CheckBox Double = dialogView.findViewById(R.id.doubleCheckBox);
        final CheckBox Triple = dialogView.findViewById(R.id.tripleCheckBox);
        final Button Save = dialogView.findViewById(R.id.saveBtn);
        final Button Cancel = dialogView.findViewById(R.id.cancelBtn);

        // Gets MainActivity
        MainActivity callingActivity = (MainActivity) getActivity();

        // References Dice from MainActivity and set's Double/Triple checkboxes to
        // boolean value of doubleScore/tripleScore from dice object.
        Double.setChecked(callingActivity.dice.getDoubleScore());
        Triple.setChecked(callingActivity.dice.getTripleScore());



        // When Save is clicked, it uses MainActivity's diceData to set the value of the dice objects
        // doubleScore/tripleScore to the boolean value of Double/Triple checkboxes isChecked
        Save.setOnClickListener(view -> {
            callingActivity.diceData(Double.isChecked(), Triple.isChecked());
            dismiss();
        });

        // When Cancel is clicked it dismisses dialog
        Cancel.setOnClickListener(view -> dismiss());


        return builder.create();
    }



}
