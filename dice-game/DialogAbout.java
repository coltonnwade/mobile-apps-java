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

public class DialogAbout extends DialogFragment {


    public DialogAbout() {
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
        View dialogView = inflater.inflate(R.layout.about, null);
        builder.setView(dialogView).setMessage("About");

        // Sets TextView/Buttons
        final TextView about = dialogView.findViewById(R.id.txtAbout);
        about.setText("\nMy name is Colton Wade! \nI am 20 years old, and I am currently working \nat a bowling alley" +
                " as a mechanic\n");

        final TextView version = dialogView.findViewById(R.id.txtVersion);
        version.setText("Version: DiceGame v7");


        final Button Ok = dialogView.findViewById(R.id.btnOk);
        // When Ok is Clicked it dismisses Dialog
        Ok.setOnClickListener(view -> dismiss());



        return builder.create();
    }
}
