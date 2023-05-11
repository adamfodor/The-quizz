package com.example.kotlinquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinquiz.databinding.ActivityEditQuestionsBinding

class editQuestions : AppCompatActivity() {


    private lateinit var dao: DAO
    private lateinit var db: ArrayList<Question>
    private lateinit var binidng: ActivityEditQuestionsBinding
    private lateinit var adapter: RecycleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binidng = ActivityEditQuestionsBinding.inflate(layoutInflater)
        setContentView(binidng.root)
        dao = DAO(applicationContext)
        db = dao.readAll()

        binidng.recyclerView.layoutManager =LinearLayoutManager(this)
        adapter = RecycleAdapter(db)
        binidng.recyclerView.adapter= adapter

        adapter.setOnItemClickListener(object :RecycleAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val builder = AlertDialog.Builder(this@editQuestions)
                builder.setTitle("Delete").setMessage("Do you want to delete this item?")
                    .setNegativeButton("No") { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    .setPositiveButton("Yes") { _, _ ->
                        dao.delete(db.get(position).getId().toString())
                        update()
                    }.create().show()

            }


        })

        binidng.btnAdd.setOnClickListener {
            val intent = Intent(this, AddQuestion::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        update()
    }

    private fun update() {
        db.clear()
        db.addAll(dao.readAll())
        this.adapter.notifyDataSetChanged()

    }

}