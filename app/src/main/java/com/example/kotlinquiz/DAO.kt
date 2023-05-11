package com.example.kotlinquiz

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DAO (var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){


    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Questiondatabase.db"
        private const val TABLE_QUESTIONS = "QuestionsTable"
        private const val KEY_ID = "id"
        private const val KEY_QUESTION = "question"
        private const val KEY_ANSWER1 = "answer1"
        private const val KEY_ANSWER2 = "answer2"
        private const val KEY_ANSWER3 = "answer3"
        private const val KEY_ANSWER4 = "answer4"
        private const val KEY_SOLUTION = "solution"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = """CREATE TABLE $TABLE_QUESTIONS(
            $KEY_ID INTEGER PRIMARY KEY,
            $KEY_QUESTION TEXT,
            $KEY_ANSWER1 TEXT,
            $KEY_ANSWER2 TEXT,
            $KEY_ANSWER3 TEXT,
            $KEY_ANSWER4 TEXT,
            $KEY_SOLUTION INTEGER
            
            ); """

        p0?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_QUESTIONS")
        onCreate(db)
    }

    fun addQuestion(q:Question) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(KEY_QUESTION,q.getQuestion())
        cv.put(KEY_ANSWER1,q.getAnswer1())
        cv.put(KEY_ANSWER2,q.getAnswer2())
        cv.put(KEY_ANSWER3,q.getAnswer3())
        cv.put(KEY_ANSWER4,q.getAnswer4())
        cv.put(KEY_SOLUTION,q.getSolution())
        val suc = db.insert(TABLE_QUESTIONS,null,cv)
        if (suc == (-1).toLong()){
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
        }
        db.close()




    }

    @SuppressLint("Range")
    fun readAll(): ArrayList<Question>{
        val list: ArrayList<Question> = ArrayList()
        val db = this.readableDatabase
        val query = """SELECT * FROM $TABLE_QUESTIONS"""
        val result = db.rawQuery(query,null)
        if (result.moveToFirst()){
            do {
                val temp = Question()
                temp.setId( result.getInt(result.getColumnIndex(KEY_ID)))
                temp.setQuestion( result.getString(result.getColumnIndex(KEY_QUESTION)))
                temp.setAns1( result.getString(result.getColumnIndex(KEY_ANSWER1)))
                temp.setAns2( result.getString(result.getColumnIndex(KEY_ANSWER2)))
                temp.setAns3( result.getString(result.getColumnIndex(KEY_ANSWER3)))
                temp.setAns4( result.getString(result.getColumnIndex(KEY_ANSWER4)))
                temp.setSol( result.getInt(result.getColumnIndex(KEY_SOLUTION)))
                list.add(temp)
            }while (result.moveToNext())
        }
        result.close()
        return list
    }

    fun delete( temp:String){
        val db = writableDatabase
        val query = """DELETE FROM $TABLE_QUESTIONS WHERE id=1"""
        db.execSQL("DELETE FROM $TABLE_QUESTIONS WHERE $KEY_ID=$temp")
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

    }


}


