package com.example.wade_colton_assignment10;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Image {



    // Sets image to appropriate dice when rolled
    public void setImages(int x, ImageView i)
    {
        if (x == 1)
            i.setImageResource(R.drawable.die_1);
        else if (x == 2)
            i.setImageResource(R.drawable.die_2);
        else if (x == 3)
            i.setImageResource(R.drawable.die_3);
        else if (x == 4)
            i.setImageResource(R.drawable.die_4);
        else if (x == 5)
            i.setImageResource(R.drawable.die_5);
        else if (x == 6)
            i.setImageResource(R.drawable.die_6);
    }

    // Sets starting images when app is loaded
    public void OnStartImages(ImageView x, ImageView y, ImageView z)
    {
        Random r = new Random();
        int num = r.nextInt(6) + 1;

        ArrayList<ImageView> Images = new ArrayList<>();
        Images.add(x);
        Images.add(y);
        Images.add(z);


        for (ImageView i : Images)
        {
            if (num == 1)
                i.setImageResource(R.drawable.die_1);
            else if (num == 2)
                i.setImageResource(R.drawable.die_2);
            else if (num == 3)
                i.setImageResource(R.drawable.die_3);
            else if (num == 4)
                i.setImageResource(R.drawable.die_4);
            else if (num == 5)
                i.setImageResource(R.drawable.die_5);
            else i.setImageResource(R.drawable.die_6);
        }

    }
}
