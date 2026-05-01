package com.example.lifehackormyth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)

        val scoreText = findViewById<TextView>(R.id.scoreTextView)
        val resultText = findViewById<TextView>(R.id.resultTextView)
        val btnReview = findViewById<Button>(R.id.reviewButton)

        //RECEIVE data from QuestionActivity
        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val questions = intent.getStringArrayExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")

        //Display score
        scoreText.text = getString(R.string.score_text, score, total)

        //Display result using IF-ELSE (good for marks)
        if (score >= 3) {
            resultText.text = getString(R.string.master)
        } else {
            resultText.text = getString(R.string.safe)
        }

        //Go to Review Screen
        btnReview.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)

            intent.putExtra("QUESTIONS", questions)
            intent.putExtra("ANSWERS", answers)

            startActivity(intent)
        }
    }
}