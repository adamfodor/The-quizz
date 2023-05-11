package com.example.kotlinquiz

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinquiz.databinding.ActivityInGameBinding
import java.util.*
import kotlin.properties.Delegates

class InGame : AppCompatActivity() {

    private lateinit var binding: ActivityInGameBinding
    private lateinit var dao: DAO
    private lateinit var inGameDB: ArrayList<Question>
    private lateinit var db: ArrayList<Question>
    private var MAXQUESTIONS by Delegates.notNull<Int>()
    private var MAXPOINT by Delegates.notNull<Int>()
    private var currentQuestion: Int = 0
    private var currentPoint: Int = 0
    private var currentAnswer: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInGameBinding.inflate(layoutInflater)
        dao = DAO(applicationContext)
        inGameDB = ArrayList<Question>()
        db = dao.readAll()

        setContentView(binding.root)
        if (db.size != 0) {
            setup()
            setQuestion(inGameDB[currentQuestion])
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Warning").setMessage("There are no strored question.\n Please add a question before you try again!")
                .setNeutralButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                }).create().show()

        }




        binding.btnAns1.setOnClickListener {
            currentAnswer = 1
            btn(currentAnswer!!)

        }
        binding.btnAns2.setOnClickListener {
            currentAnswer = 2
            btn(currentAnswer!!)


        }
        binding.btnAns3.setOnClickListener {
            currentAnswer = 3
            btn(currentAnswer!!)


        }
        binding.btnAns4.setOnClickListener {
            currentAnswer = 4
            btn(currentAnswer!!)


        }


    }

    private fun setup() {


        if (db.size < 10) {
            inGameDB = db
            MAXQUESTIONS = inGameDB.size
            MAXPOINT = inGameDB.size
            binding.tvPoints.text = (String.format("%d/%d", currentPoint, MAXPOINT))
            binding.tvQnums.text = (String.format("%d/%d", currentQuestion, MAXQUESTIONS))
        } else {
            val rand = Random()
            for (i in 0 until 10) {
                val temp = rand.nextInt(db.size)
                inGameDB.add(db[temp])
            }

        }
        inGameDB.shuffle()
    }


    private fun setQuestion(q: Question) {
        binding.tvQuestion.text = q.getQuestion()
        binding.btnAns1.text = q.getAnswer1()
        binding.btnAns2.text = q.getAnswer2()
        binding.btnAns3.text = q.getAnswer3()
        binding.btnAns4.text = q.getAnswer4()
    }

    private fun chehckAnswer(temp: Int) {
        if (temp == inGameDB[currentQuestion].getSolution()) {
            currentPoint++
            binding.tvPoints.text = (String.format("%d/%d", currentPoint, MAXPOINT))
        }
    }

    private fun end() {


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Finish")
            .setMessage(String.format("Your point: %d/%d", currentPoint, MAXPOINT))
            .setNeutralButton("Continue") { _, _ ->
                finish()
            }.create().show()

    }

    private fun btn(temp: Int) {
        chehckAnswer(temp)
        currentQuestion++
        if (currentQuestion < inGameDB.size) {
            binding.tvQnums.text = (String.format("%d/%d", currentQuestion, MAXQUESTIONS))

            setQuestion(inGameDB[currentQuestion])

        } else {
            end()


        }
    }
}