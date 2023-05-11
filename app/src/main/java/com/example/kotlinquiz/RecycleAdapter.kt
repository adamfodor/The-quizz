package com.example.kotlinquiz

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter(private val db: ArrayList<Question>) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.carddesign, parent, false)
        return ViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = db[position]
        holder.question.text = temp.getQuestion()
        holder.opt1.text = temp.getAnswer1()
        holder.opt2.text = temp.getAnswer2()
        holder.opt3.text = temp.getAnswer3()
        holder.opt4.text = temp.getAnswer4()
        if (temp.getSolution()==1) {
            holder.opt1.setTextColor(Color.GREEN)
        }else if (temp.getSolution()==2){
            holder.opt2.setTextColor(Color.GREEN)
        }else if (temp.getSolution()==3){
            holder.opt3.setTextColor(Color.GREEN)
        }else if (temp.getSolution()==4){
            holder.opt4.setTextColor(Color.GREEN)
        }


    }

    override fun getItemCount(): Int {
        return db.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val question: TextView = itemView.findViewById(R.id.tv_question)
        val opt1: TextView = itemView.findViewById(R.id.tv_ans1)
        val opt2: TextView = itemView.findViewById(R.id.tv_ans2)
        val opt3: TextView = itemView.findViewById(R.id.tv_ans3)
        val opt4: TextView = itemView.findViewById(R.id.tv_ans4)


        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }


    }

}