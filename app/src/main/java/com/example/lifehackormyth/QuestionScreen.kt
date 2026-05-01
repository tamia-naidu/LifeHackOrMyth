package com.example.lifehackormyth

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    // list of all questions
    private val questions = arrayOf(
        "Backing up your data prevents data loss",
        "Public Wi-Fi is always safe if it has a password",
        "Incognito mode makes you anonymous",
        "Writing things down helps memory",
        "Dark mode saves battery on all devices"
    )

    // correct answers (true = hack, false = myth)
    private val answers = booleanArrayOf(
        true, false, false, true, false
    )

    private var currentIndex = 0

    // Stores the score
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_question_screen)

        // Links to UI
        val questionText = findViewById<TextView>(R.id.questionTextView)
        val feedbackText = findViewById<TextView>(R.id.txtFeedback)
        val btnTrue = findViewById<Button>(R.id.trueButton)
        val btnFalse = findViewById<Button>(R.id.falseButton)
        val btnNext = findViewById<Button>(R.id.nextButton)

        // Function to display the current question
        fun loadQuestion() {
            questionText.text = questions[currentIndex]
            feedbackText.text = ""
        }

        // Load first question
        loadQuestion()

        // True button click
        btnTrue.setOnClickListener {
            checkAnswer(true, feedbackText)
        }

        // False button click
        btnFalse.setOnClickListener {
            checkAnswer(false, feedbackText)
        }

        // Next button click
        btnNext.setOnClickListener {

            // Move to next question
            currentIndex++

            // WHILE LOOP used to control iteration through questions
            while (currentIndex < questions.size) {
                loadQuestion() // display next question
                break          // stop loop so only ONE question shows at a time
            }

            // If all questions are completed
            if (currentIndex >= questions.size) {

                val intent = Intent(this, ScoreActivity::class.java)

                // Pass data to next screen
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                intent.putExtra("QUESTIONS", questions)
                intent.putExtra("ANSWERS", answers)

                startActivity(intent)
                finish()
            }
        }
    }

    // Function to check if user selected correct answer
    private fun checkAnswer(userAnswer: Boolean, feedbackText: TextView) {

        if (userAnswer == answers[currentIndex]) {
            score++
            feedbackText.text = getString(R.string.correct_answer)
        } else {
            feedbackText.text = getString(R.string.wrong_answer)
        }
    }
}