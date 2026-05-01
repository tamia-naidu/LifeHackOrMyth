package com.example.lifehackormyth

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_screen)

        val reviewText = findViewById<TextView>(R.id.txtReview)

        val questions = intent.getStringArrayExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")

        val builder = StringBuilder()

        if (questions != null && answers != null) {
            for (i in questions.indices) {
                val correct = if (answers[i]) "Hack (True)" else "Myth (False)"

                builder.append("${i + 1}. ${questions[i]}\n")
                builder.append("Correct Answer: $correct\n\n")
            }
        }

        reviewText.text = builder.toString()
    }
}