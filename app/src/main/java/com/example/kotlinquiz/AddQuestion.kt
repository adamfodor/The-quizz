package com.example.kotlinquiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinquiz.databinding.ActivityAddQuestionBinding
import kotlinx.android.synthetic.main.activity_add_question.*

class AddQuestion : AppCompatActivity() {
    private lateinit var binidng: ActivityAddQuestionBinding
    private val dao = DAO(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binidng = ActivityAddQuestionBinding.inflate(layoutInflater)
        setContentView(binidng.root)




        binidng.floatingAdd.setOnClickListener {
            addQ()

        }
        binidng.floatDel.setOnClickListener {
            binidng.etQuestion.text.clear()
            binidng.etOpt1.text.clear()
            binidng.etOpt2.text.clear()
            binidng.etOpt3.text.clear()
            binidng.etOpt4.text.clear()
            binidng.rdbtnGroup.clearCheck()
            Toast.makeText(applicationContext,"Cleared",Toast.LENGTH_SHORT).show()
        }


    }

    private fun addQ() {
        val q = binidng.etQuestion.text.toString()
        val ans1 =binidng.etOpt1.toString()
        val ans2 = binidng.etOpt2.text.toString()
        val ans3 = binidng.etOpt3.text.toString()
        val ans4 = binidng.etOpt4.text.toString()

        var answer: Int? = null
        if (ans_opt1.isChecked) {
            answer = 1
        } else if (ans_opt2.isChecked) {
            answer = 2
        } else if (ans_opt3.isChecked) {
            answer = 3
        } else if (ans_opt4.isChecked) {
            answer = 4
        }
        if (binidng.ansOpt1.isChecked || binidng.ansOpt2.isChecked || binidng.ansOpt4.isChecked || binidng.ansOpt4.isChecked) {
            if (isEmpty(q) && isEmpty(ans1) && isEmpty(ans2) && isEmpty(ans4) && isEmpty(ans4)) {
                val temp = Question()
                temp.setQuestion(q)
                temp.setAns1(ans1)
                temp.setAns2(ans2)
                temp.setAns3(ans3)
                temp.setAns4(ans4)
                temp.setSol(answer!!)

                dao.addQuestion(temp)
                finish()
            } else {
                Toast.makeText(this, "Something is empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Choose an answer!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isEmpty(temp: String): Boolean {
        return temp.trim().isNotEmpty()
    }
}