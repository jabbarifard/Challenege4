package com.example.challenge4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.challenge4.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    int[] count;
    int backCount;
    int id1, id2 = 0;
    int score = 0;
    int frontCount = 0;
    List<Integer> copyIds;
    List<Integer> matched;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        count = new int[8];
        backCount = 0;
        frontCount = 0;
        score = 0;

        int[] drawableIDs = {R.drawable.amazed, R.drawable.crying, R.drawable.hearteyes, R.drawable.heartface, R.drawable.laughing, R.drawable.ok, R.drawable.sad, R.drawable.thinking};
        copyIds = new ArrayList<>(drawableIDs.length);
        matched = new ArrayList<>();
        for(int i=0; i<drawableIDs.length; i++){
            copyIds.add(drawableIDs[i]);
        }

        cardOnClick(binding.card1View);
        cardOnClick(binding.card2View);
        cardOnClick(binding.card3View);
        cardOnClick(binding.card4View);
        cardOnClick(binding.card5View);
        cardOnClick(binding.card6View);
        cardOnClick(binding.card7View);
        cardOnClick(binding.card8View);
        cardOnClick(binding.card9View);
        cardOnClick(binding.card10View);
        cardOnClick(binding.card11View);
        cardOnClick(binding.card12View);
        cardOnClick(binding.card13View);
        cardOnClick(binding.card14View);
        cardOnClick(binding.card15View);
        cardOnClick(binding.card16View);
    }

    public void cardOnClick(ImageView view){

        Integer[] drawableIDs = {R.drawable.amazed, R.drawable.crying, R.drawable.hearteyes, R.drawable.heartface, R.drawable.laughing, R.drawable.ok, R.drawable.sad, R.drawable.thinking};
        final Context context = this;
        //int id = drawableIDs[new Random().nextInt(8)];
        int id = copyIds.get(new Random().nextInt(copyIds.size()));
        Log.v("This is id: ", Integer.toString(id));
        int index = Arrays.asList(drawableIDs).indexOf(id);
        Log.v("This is drawableIDs: ", Arrays.toString(drawableIDs));
        Log.v("This is index: ", Integer.toString(index));
        count[index]++;
        Log.v("This is count: ", Integer.toString(count[index]));
        if (count[index] == 2){
            copyIds.remove((Integer) id);
        }
        Log.v("This is copyIds: ", copyIds.toString());
        Drawable drawable= context.getResources().getDrawable(id);
        view.setImageResource(R.drawable.emojipattern);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.emojipattern).getConstantState()){
                    if(backCount < 2) {
                        if (backCount == 0) {//backCount = 0
                            id1 = id;
                            Log.v("This is id1: ", Integer.toString(id1));
                            //Log.v("This is backCount: ", Integer.toString(backCount));
                        } else {//backCount = 1
                            id2 = id;
                            Log.v("This is id2: ", Integer.toString(id2));
                            //Log.v("This is backCount: ", Integer.toString(backCount));
                        }
                        view.setImageDrawable(drawable);
                        backCount++;
                        Log.v("This is backCount: ", Integer.toString(backCount));
                    }
                    if (backCount == 2) {
                        if (id1 == id2) {
                            Log.v("This is score: ", Integer.toString(score));
                            score++;
                            frontCount += 2;
                            if(frontCount != 16){ alertView("Matched!");}
                            matched.add(id1);
                            matched.add(id2);
                            Log.v("This is matched: ", matched.toString());
                            backCount = 0;
                        } else {
                            score++;
                            alertView("Please flip the cards and try a new pair!");
                        }
                        //score++;
                    }
                } else {//backCount = 2
                    if (matched.contains(id)) {//click on the front emoji
                        alertView("You cannot flip matched cards!");
                    } else {
                        //set the image view to display the drawable
                        view.setImageResource(R.drawable.emojipattern);
                        backCount--;
                    }
                }

                if(frontCount == 16){
                    alertView("Your Score: "+ score);
                    Log.v("This is frontCount: ", Integer.toString(frontCount));
                }
            }
        });
    }

    public void resetOnClick(View view){
        count = new int[8];
        backCount = 0;
        frontCount = 0;
        score = 0;
        matched = new ArrayList<>();
        int[] drawableIDs = {R.drawable.amazed, R.drawable.crying, R.drawable.hearteyes, R.drawable.heartface, R.drawable.laughing, R.drawable.ok, R.drawable.sad, R.drawable.thinking};
        copyIds = new ArrayList<>(drawableIDs.length);
        for(int i=0; i<drawableIDs.length; i++){
            copyIds.add(drawableIDs[i]);
        }
        cardOnClick(binding.card1View);
        cardOnClick(binding.card2View);
        cardOnClick(binding.card3View);
        cardOnClick(binding.card4View);
        cardOnClick(binding.card5View);
        cardOnClick(binding.card6View);
        cardOnClick(binding.card7View);
        cardOnClick(binding.card8View);
        cardOnClick(binding.card9View);
        cardOnClick(binding.card10View);
        cardOnClick(binding.card11View);
        cardOnClick(binding.card12View);
        cardOnClick(binding.card13View);
        cardOnClick(binding.card14View);
        cardOnClick(binding.card15View);
        cardOnClick(binding.card16View);
    }
    private void alertView(String message) {
        builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  Action for 'Ok' Button
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle(message);
        alert.show();
    }
}
