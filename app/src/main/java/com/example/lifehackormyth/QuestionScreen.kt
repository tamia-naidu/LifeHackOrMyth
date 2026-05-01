package com.example.lifehackormyth

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    // Array storing all questions
    private val questions = arrayOf(
        "Backing up your data prevents data loss",
        "Public Wi-Fi is always safe if it has a password",
        "Incognito mode makes you anonymous",
        "Drinking water helps concentration",
        "Dark mode saves battery on all devices"
    )

    // Array storing correct answers (true = hack, false = myth)
    private val answers = booleanArrayOf(
        true, false, false, true, false
    )

    // Keeps track of current question index
    private var currentIndex = 0

    // Stores user's score
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load the question screen layout
        setContentView(R.layout.activity_question_screen)

        // Link UI components
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

        // TRUE button click
        btnTrue.setOnClickListener {
            checkAnswer(true, feedbackText)
        }

        // FALSE button click
        btnFalse.setOnClickListener {
            checkAnswer(false, feedbackText)
        }

        // NEXT button click
        btnNext.setOnClickListener {

            // Move to next question
            currentIndex++

            // WHILE LOOP used to control iteration through questions
            // This ensures we only display valid questions within array bounds
            while (currentIndex < questions.size) {
                loadQuestion() // display next question
                break          // stop loop so only ONE question shows at a time
            }

            // If all questions are completed
            if (currentIndex >= questions.size) {

                // Navigate to Score screen
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

        // IF-ELSE statement to check correctness
        if (userAnswer == answers[currentIndex]) {
            score++ // increase score if correct
            val selected = ""
            feedbackText.text = "$selected\n${getString(R.string.correct)}"
        } else {
            val selected = ""
            feedbackText.text = "$selected\n${getString(R.string.wrong)}"
        }
    }
}