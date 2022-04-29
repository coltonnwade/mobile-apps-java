package com.example.wade_colton_assignment10;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationManager implements Animation.AnimationListener{


    // Animations
    Animation bounce, rightLeft, topBottom, rotateRight;


    // Methods to set animations to different views
    public void TopBottom(View v, int duration)
    {
        topBottom.setDuration(duration);
        topBottom.setAnimationListener(this);
        v.startAnimation(topBottom);
    }

    public void RightLeft(View v, int duration)
    {
        rightLeft.setDuration(duration);
        rightLeft.setAnimationListener(this);
        v.startAnimation(rightLeft);
    }

    public void Bounce(View v, int duration)
    {
        bounce.setDuration(duration);
        bounce.setAnimationListener(this);
        v.startAnimation(bounce);
    }

    public void RotateRight(View v, int duration)
    {
        rotateRight.setDuration(duration);
        rotateRight.setAnimationListener(this);
        v.startAnimation(rotateRight);
    }


    // Loads Animations
    public void loadAnimations(Context context) {
        rightLeft = AnimationUtils.loadAnimation(context, R.anim.right_left);
        topBottom = AnimationUtils.loadAnimation(context, R.anim.top_bot);
        bounce = AnimationUtils.loadAnimation(context, R.anim.bounce);
        rotateRight = AnimationUtils.loadAnimation(context, R.anim.rotate_right);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
