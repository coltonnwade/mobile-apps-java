package com.example.wade_colton_assignment10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener{


    // TextView's
    private ImageView diceOneView, diceTwoView, diceThreeView;
    private TextView totalScoreView, currentScoreView, scoreEnhancerView, SwipeMessage;
    private EditText UsernameView;

    // Gestures
    private GestureDetectorCompat gDetect;
    // Minimal x and y axis swipe distance.
    final static int MIN_SWIPE_DISTANCE_X = 100;
    final static int MIN_SWIPE_DISTANCE_Y = 100;
    // Maximal x and y axis swipe distance.
    final static int MAX_SWIPE_DISTANCE_X = 1000;
    final static int MAX_SWIPE_DISTANCE_Y = 1000;

    // Classes
    Dice dice = new Dice();
    Image Img = new Image();
    AnimationManager am = new AnimationManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUIElements(); // Sets Elements
        Img.OnStartImages(diceOneView, diceTwoView, diceThreeView); // Calls SetImages Method
        getReceivedIntent(); // getsIntent from ScoreboardActivity

        am.loadAnimations(this); // Loads Animations
        this.gDetect = new GestureDetectorCompat(this, this); // Gesture Detector

        // Scoreboard Button
        Button Scoreboard = findViewById(R.id.btnScoreboard);
        Scoreboard.setOnClickListener(view -> {
            sendIntent();
        });

        // Animate Views
        am.TopBottom(diceOneView, 1000);
        am.TopBottom(diceTwoView, 1000);
        am.TopBottom(diceThreeView, 1000);
        am.RotateRight(SwipeMessage, 1000);
    }

    @SuppressLint("SetTextI18n")
    public void RollDice()
    {
        String username = UsernameView.getText().toString();
        // If username is empty it will display a error message with a text view
        if (!username.equals("")) {
            existingUser();
            CallMediaPlayer();
            SwipeMessage.setText("");
            UsernameChanged(); // Checks to see if Username has Changed

            dice.rollDice(); // Roll's Dice
            scoreEnhancer(); // Checks to see if score is enhanced
            displayData();  // Display's Data
            AnimateDice(); // Animates Dice

            SharedPreferences Stats = getSharedPreferences("DiceGame", MODE_PRIVATE); // Creates SharedPreferences Stats
            SharedPreferences.Editor myEdit = Stats.edit(); // Creates SharedPreferences editor myEdit

            // Creates Player
            Player player = new Player(username, dice.getTotalScore(), dice.getDoubles(), dice.getTriples(), dice.getRolls());

            // Stores object in shared preferences (username/player)
            Gson gson = new Gson();
            String json = gson.toJson(player);
            myEdit.putString(username, json);
            myEdit.apply(); // Apply Edit

        }
        else
        {
            SwipeMessage.setText("You Must Enter A Name!");
            am.Bounce(SwipeMessage, 1000); // Brings attention to error message
        }
    }

    @SuppressLint("SetTextI18n")
    // ScoreEnhancer display's
    public void scoreEnhancer() {
        scoreEnhancerView.setText("No Enhancer");

        // if isTripleScore is true scoreEnhancerView displays triple message
        if(dice.isTripleScore()) {
            scoreEnhancerView.setText("Triple: 100!");
        }
        // if isDoubleScore is true scoreEnhancerView displays double message
        else if(dice.isDoubleScore()) {
            scoreEnhancerView.setText("Double: +50!");
        }
    }
    
    // Gets diceData from DialogSettings and sets dices Double and Triple to true/false
    public void diceData(boolean d, boolean t) {
        dice.setDoubleScore(d);
        dice.setTripleScore(t);

        // Debug
        Log.i("SETTINGS INFO", String.format("Double %s",dice.getDoubleScore()));
        Log.i("SETTINGS INFO", String.format("Triple %s",dice.getTripleScore()));
    }

    // Display's Data
    public void displayData()
    {
        Img.setImages(dice.getDiceOne(), diceOneView);
        Img.setImages(dice.getDiceTwo(), diceTwoView);
        Img.setImages(dice.getDiceThree(), diceThreeView);
        currentScoreView.setText(String.format("Current Score: %s", dice.getCurrentScore()));
        totalScoreView.setText(String.format("Score: %s", dice.getTotalScore()));
    }

    // Set's UI elements
    @SuppressLint("SetTextI18n")
    public void setUIElements() {
        diceOneView = findViewById(R.id.diceOneImgView); // Creates dice one image view
        diceTwoView = findViewById(R.id.diceTwoImgView); // Creates dice two image view
        diceThreeView = findViewById(R.id.diceThreeImgView); // Creates dice three image view
        totalScoreView = findViewById(R.id.totalScoreTxt); // creates total score text view
        currentScoreView = findViewById(R.id.currentScoreTxt); // creates current score text view
        scoreEnhancerView = findViewById(R.id.scoreEnhancerTxt); // creates score enhancer text view
        UsernameView = findViewById(R.id.plainTxtUsername); // creates username plain text view
        SwipeMessage = findViewById(R.id.txtViewSwipeMessage); // creates swipe message text view
    }

    // Reset's the app and Clears Shared Preferences
    public void resetGame() {
        SharedPreferences Stats = getSharedPreferences("DiceGame", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = Stats.edit();
        myEdit.clear();
        myEdit.apply();
        UsernameView.setText("");
        scoreEnhancerView.setText("");
        dice.resetStats();
        displayData();
        Img.OnStartImages(diceOneView, diceTwoView,diceThreeView);
    }

    // region Users/Username Changed
    // When text changes, it will call newUser();
    public void UsernameChanged() {
        UsernameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                newUser();
            }
        });
    }

    // Resets app for new user
    public void newUser() {
        dice.resetStats();
        displayData();
        scoreEnhancerView.setText("");
        Img.OnStartImages(diceOneView, diceTwoView,diceThreeView);
        existingUser();

    }

    public void existingUser()
    {
        SharedPreferences Stats = getSharedPreferences("DiceGame", MODE_PRIVATE); // Gets SharedPreferences
        Map<String, ?> allEntries = (Map<String, ?>) Stats.getAll(); // Creates Map of all Shared Preferences
        for(Map.Entry<String, ?> entry : allEntries.entrySet()) {


            if (entry.getKey().equals(UsernameView.getText().toString()))
            {
                Gson gson = new Gson();
                String json = Stats.getString(entry.getKey(), entry.getValue().toString());
                Player existingPlayer = gson.fromJson(json, Player.class);

                dice.setTotalScore(existingPlayer.getTotalScore());
                totalScoreView.setText(String.format("Score: %s", existingPlayer.getTotalScore()));
            }

        }
    }
    // endregion Users/Username Changed

    // region Options Menu
    @Override
    // Creates Option Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    // Select Options
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Opens Settings Dialog
        if(id == R.id.settings) {
            DialogSettings settingsDialog = new DialogSettings();
            settingsDialog.show(getSupportFragmentManager(), "settings");
            return true;
        }
        // Opens About Dialog
        else if (id == R.id.about) {
            DialogAbout myDialog = new DialogAbout();
            myDialog.show(getSupportFragmentManager(), "about");
            return true;
        }
        // Opens Stats Dialog
        else if(id == R.id.stats) {
            DialogStats statsDialog = new DialogStats();
            statsDialog.show(getSupportFragmentManager(), "stats");
            return true;
        }
        // Returns Selected Dialog
        return super.onOptionsItemSelected(item);
    }
    // endregion OptionsMenu

    // region Save/RestoreInstanceState
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Stores Data
        outState.putParcelable("dice", dice);
        outState.putString("enhance", String.valueOf(scoreEnhancerView));
        outState.putString("username", UsernameView.getText().toString());
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restores Data
        this.dice = savedInstanceState.getParcelable("dice");
        scoreEnhancerView.setText(savedInstanceState.getString("enhance"));
        UsernameView.setText(savedInstanceState.getString("username"));
        displayData();
    }
    // endregion Save/RestoreInstanceState

    // region Animations
    // Animates Dice
    public void AnimateDice()
    {
        if (dice.isTripleScore())
        {
            // Animates DiceView's
            am.RotateRight(diceOneView, 5000);
            am.RotateRight(diceTwoView,1000);
            am.RotateRight(diceThreeView, 1000);
        }
        else if (dice.isDoubleScore())
        {
            // Animates DiceView's
            am.RotateRight(diceOneView, 5000);
            am.RotateRight(diceTwoView,1000);
            am.RotateRight(diceThreeView, 1000);
        }
        else
        { // Animates DiceView's
            am.Bounce(diceOneView, 5000);
            am.Bounce(diceTwoView,1000);
            am.Bounce(diceThreeView, 1000);

        }
    }
    // endregion Animations

    // region MediaPlayer
    // Media Player
    public void CallMediaPlayer()
    {
        // Creates Media Player
        final MediaPlayer mpRoll = MediaPlayer.create(this, R.raw.roll_dice); // Media Player

        // When mpRoll is prepared, it will start and set the volume of the media player
        mpRoll.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mpRoll.start();
                mpRoll.setVolume(100, 100);

            }
        });

        // On completion it will reset, and release the media player from idle, to end state.
        mpRoll.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mpRoll.reset();
                mpRoll.release();
            }
        });

        // If Media Player throws an error, it will reset the Media Player
        mpRoll.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                mpRoll.reset();
                return false;
            }
        });
    }
    // endregion MediaPlayer

    // region Send/Receive Intents
    // Sends Intent
    public void sendIntent()
    {
        // Creates Intent
        Intent intent = new Intent(getBaseContext(), ScoreboardActivity.class);
        Gson gson = new Gson();
        String jsonDice = gson.toJson(dice);
        intent.putExtra("Dice", jsonDice);
        intent.putExtra("username", UsernameView.getText().toString()); // sends Username to ScoreboardActivity
        startActivity(intent);
    }

    // Receives Intent
    public void getReceivedIntent()
    {
        // Gets Intent from ScoreboardActivity
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            Gson gson = new Gson();
            String jsonDice = bundle.getString("Dice");
            Dice bundleDice = gson.fromJson(jsonDice, Dice.class);
            String username = bundle.getString("player");

            // Sets views
            currentScoreView.setText(String.format("Current Score: %s", bundleDice.getCurrentScore()));
            dice.setCurrentScore(bundleDice.getCurrentScore());

            totalScoreView.setText(String.format("Score: %s", bundleDice.getTotalScore()));
            dice.setTotalScore(bundleDice.getTotalScore());

            dice.setRolls(bundleDice.getRolls());
            Img.setImages(bundleDice.getDiceOne(), diceOneView);
            Img.setImages(bundleDice.getDiceTwo(), diceTwoView);
            Img.setImages(bundleDice.getDiceThree(), diceThreeView);
            UsernameView.setText(username);

        }
    }
    // endregion Send/ReceiveIntents

    // region Gestures
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        sendIntent();
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float X, float Y) {
        // Getting DeltaX/DeltaY
        float deltaX = event1.getX() - event2.getX();
        float deltaY = event1.getY() - event2.getY();

        // Getting Absolute DeltaX/DeltaY
        float DeltaXAbs = Math.abs(deltaX);
        float DeltaYAbs = Math.abs(deltaY);

        // X Axis
        if((DeltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaX <= MAX_SWIPE_DISTANCE_X))
        {
            if(deltaX > 0) {
                // Left Swipe
                Log.i("TOUCH_INFO", "User has Swiped Left");
                // If the user swipes left, the dice will roll
            }
            else {

                Log.i("TOUCH_INFO", "User has Swiped Right");
                // If the user swipes to the right, the scoreboard intent will appear.
             }
            RollDice();

        }
        // Y Axis
        else if((DeltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (DeltaYAbs <= MAX_SWIPE_DISTANCE_Y))
        {
            if(deltaY > 0) {
                Log.i("TOUCH_INFO", "User has Swiped Up");
            }
            else {
                Log.i("TOUCH_INFO", "User has Swiped Down");
            }
            // If user swipes up or down, the dice will roll.
            RollDice();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    // endregion Gestures
}