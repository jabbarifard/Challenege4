package com.example.challenge4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge4.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    var timer: Timer? = null
    lateinit var count: IntArray
    var backCount = 0
    var id1 = 0
    var id2 = 0
    var score = 0
    var frontCount = 0
    var copyIds: MutableList<Int>? = null
    var matched: MutableList<Int>? = null
    var builder: AlertDialog.Builder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mainT = new Timer (10,this);
        //mainT.();
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.getRoot()
        setContentView(view)
        count = IntArray(8)
        backCount = 0
        frontCount = 0
        score = 0
        //timer = new Timer(120);
        //timer.run();
        val drawableIDs = intArrayOf(R.drawable.amazed, R.drawable.crying, R.drawable.hearteyes, R.drawable.heartface, R.drawable.laughing, R.drawable.ok, R.drawable.sad, R.drawable.thinking)
        copyIds = ArrayList(drawableIDs.size)
        matched = ArrayList()
        for (drawableID in drawableIDs) {
            (copyIds as ArrayList<Int>).add(drawableID)
        }
        cardOnClick(binding!!.card1View)
        cardOnClick(binding!!.card2View)
        cardOnClick(binding!!.card3View)
        cardOnClick(binding!!.card4View)
        cardOnClick(binding!!.card5View)
        cardOnClick(binding!!.card6View)
        cardOnClick(binding!!.card7View)
        cardOnClick(binding!!.card8View)
        cardOnClick(binding!!.card9View)
        cardOnClick(binding!!.card10View)
        cardOnClick(binding!!.card11View)
        cardOnClick(binding!!.card12View)
        cardOnClick(binding!!.card13View)
        cardOnClick(binding!!.card14View)
        cardOnClick(binding!!.card15View)
        cardOnClick(binding!!.card16View)
    }

    fun cardOnClick(view: ImageView) {
        val drawableIDs = arrayOf(R.drawable.amazed, R.drawable.crying, R.drawable.hearteyes, R.drawable.heartface, R.drawable.laughing, R.drawable.ok, R.drawable.sad, R.drawable.thinking)
        val context: Context = this
        //int id = drawableIDs[new Random().nextInt(8)];
        val id = copyIds!![Random().nextInt(copyIds!!.size)]
        Log.v("This is id: ", Integer.toString(id))
        val index = Arrays.asList(*drawableIDs).indexOf(id)
        Log.v("This is drawableIDs: ", Arrays.toString(drawableIDs))
        Log.v("This is index: ", Integer.toString(index))
        count[index]++
        Log.v("This is count: ", Integer.toString(count[index]))
        if (count[index] == 2) {
            copyIds!!.remove(id)
        }
        Log.v("This is copyIds: ", copyIds.toString())
        val drawable = context.resources.getDrawable(id)
        view.setImageResource(R.drawable.emojipattern)
        //        timer = new Timer(30);
//        timer.run();
        view.setOnClickListener { //                timer = new Timer(30);
//                timer.run();
            if (view.drawable.constantState === resources.getDrawable(R.drawable.emojipattern).constantState) {
                if (backCount < 2) {
                    if (backCount == 0) { //backCount = 0
                        id1 = id
                        Log.v("This is id1: ", Integer.toString(id1))
                        //Log.v("This is backCount: ", Integer.toString(backCount));
                    } else { //backCount = 1
                        id2 = id
                        Log.v("This is id2: ", Integer.toString(id2))
                        //Log.v("This is backCount: ", Integer.toString(backCount));
                    }
                    view.setImageDrawable(drawable)
                    backCount++
                    Log.v("This is backCount: ", Integer.toString(backCount))
                }
                if (backCount == 2) {
                    if (id1 == id2) {
                        Log.v("This is score: ", Integer.toString(score))
                        score++
                        frontCount += 2
                        if (frontCount != 16) {
                            alertView("Matched!")
                        }
                        matched!!.add(id1)
                        matched!!.add(id2)
                        Log.v("This is matched: ", matched.toString())
                        backCount = 0
                    } else {
                        score++
                        alertView("Please flip the cards and try a new pair!")
                    }
                    //score++;
                }
            } else { //backCount = 2
                if (matched!!.contains(id)) { //click on the front emoji
                    alertView("You cannot flip matched cards!")
                } else {
                    //set the image view to display the drawable
                    view.setImageResource(R.drawable.emojipattern)
                    backCount--
                }
            }
            if (frontCount == 16) {
                alertView("Your Score: $score")
                Log.v("This is frontCount: ", Integer.toString(frontCount))
            }
        }
    }

    fun resetOnClick(view: View?) {
        count = IntArray(8)
        backCount = 0
        frontCount = 0
        score = 0
        matched = ArrayList()
        val drawableIDs = intArrayOf(R.drawable.amazed, R.drawable.crying, R.drawable.hearteyes, R.drawable.heartface, R.drawable.laughing, R.drawable.ok, R.drawable.sad, R.drawable.thinking)
        copyIds = ArrayList(drawableIDs.size)
        for (i in drawableIDs.indices) {
            (copyIds as ArrayList<Int>).add(drawableIDs[i])
        }
        cardOnClick(binding!!.card1View)
        cardOnClick(binding!!.card2View)
        cardOnClick(binding!!.card3View)
        cardOnClick(binding!!.card4View)
        cardOnClick(binding!!.card5View)
        cardOnClick(binding!!.card6View)
        cardOnClick(binding!!.card7View)
        cardOnClick(binding!!.card8View)
        cardOnClick(binding!!.card9View)
        cardOnClick(binding!!.card10View)
        cardOnClick(binding!!.card11View)
        cardOnClick(binding!!.card12View)
        cardOnClick(binding!!.card13View)
        cardOnClick(binding!!.card14View)
        cardOnClick(binding!!.card15View)
        cardOnClick(binding!!.card16View)
    }

    private fun alertView(message: String) {
        builder = AlertDialog.Builder(this)
        builder!!.setNegativeButton("Ok") { dialog, id -> //  Action for 'Ok' Button
            dialog.cancel()
        }
        val alert = builder!!.create()
        alert.setTitle(message)
        alert.show()
    } //    public static void timer(){
    //        boolean x=true;
    //        long displayMinutes=0;
    //        long starttime=System.currentTimeMillis();
    //        System.out.println("Timer:");
    //        while(x)
    //        {
    //            TimeUnit.SECONDS.sleep(1);
    //            long timepassed=System.currentTimeMillis()-starttime;
    //            long secondspassed=timepassed/1000;
    //            if(secondspassed==60)
    //            {
    //                secondspassed=0;
    //                starttime=System.currentTimeMillis();
    //            }
    //            if((secondspassed%60)==0)
    //                displayMinutes++;
    //
    //            System.out.println(displayMinutes+"::"+secondspassed);
    //        }
    ////
    ////        int count = 0;
    ////        while (count<10) {
    ////            try {
    ////                Thread.sleep(1000);
    ////
    ////            } catch (Exception e) {
    ////            }
    ////            count++;
    ////        }
    ////        System.out.println(count);
    //    }
}