package com.spencerpotter.madlibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //layout of the first screen
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL


        //layout of the screen after the button is pressed
        val refreshLayout = LinearLayout(this)
        refreshLayout.orientation = LinearLayout.VERTICAL


        // All of the textview variables the app needs
        val adjective = TextView(this)
        adjective.text = "Enter an adjective"

        val adjective2 = TextView(this)
        adjective2.text = "Enter an adjective"

        val noun = TextView(this)
        noun.text = "Enter a noun"

        val noun2 = TextView(this)
        noun2.text = "Enter a noun"

        val animal = TextView(this)
        animal.text = "Enter an animal (Plural)"

        val game = TextView(this)
        game.text = "Enter a game"

        //All of the editText variables we will need
        val editAdj = EditText(this)
        val editAdj2 = EditText(this)
        val editNoun = EditText(this)
        val editNoun2 = EditText(this)
        val editAni = EditText(this)
        val editGame = EditText(this)

        //setting up the button
        val button = Button(this)
        button.text = "Generate Mad Lib"
        button.setOnClickListener({
            //turning the inputs into strings and setting up the madlib
            val inputAdj =  editAdj.text.toString()
            val inputAdj2 = editAdj2.text.toString()
            val inputNoun = editNoun.text.toString()
            val inputNoun2 = editNoun2.text.toString()
            val inputAni = editAni.text.toString()
            val inputGame = editGame.text.toString()
            val output = TextView(this)

            //creating the madlib story
            val madLib = "A vacation is when you take a trip to some ".plus(inputAdj)
                .plus(" place with your ").plus(inputAdj2)
                .plus(" family. Usually you go to some place that is near a ").plus(inputNoun)
                .plus(" or up on a ").plus(inputNoun2)
                .plus(". A good vacation place is one where you can ride ").plus(inputAni)
                .plus( " or play ").plus(inputGame)
            output.text = madLib

            //refreshing the screen to display the mad lib
            refreshLayout.addView(output)
            setContentView(refreshLayout)

        })


        //setting up the layout to get the input from the user
        layout.addView(adjective)
        layout.addView(editAdj)
        layout.addView(adjective2)
        layout.addView(editAdj2)
        layout.addView(noun)
        layout.addView(editNoun)
        layout.addView(noun2)
        layout.addView(editNoun2)
        layout.addView(animal)
        layout.addView(editAni)
        layout.addView(game)
        layout.addView(editGame)
        layout.addView(button)
        setContentView(layout)

    }
}