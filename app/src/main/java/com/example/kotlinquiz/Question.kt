package com.example.kotlinquiz

class Question () {

    private var id :Int =1
    private var question:String =""
    private var answer1:String=""
    private var answer2:String=""
    private var answer3:String=""
    private var answer4:String=""
    private var solution:Int=0




    fun getQuestion():String = this.question
    fun getAnswer1():String = this.answer1
    fun getAnswer2():String = this.answer2
    fun getAnswer3():String = this.answer3
    fun getAnswer4():String = this.answer4
    fun getSolution(): Int = this.solution
    fun getId():Int = this.id



    fun setId(value:Int){
        this.id = value
    }
    fun setQuestion(value:String){
        this.question = value
    }
    fun setAns1(value:String){
        this.answer1 = value
    }
    fun setAns2(value:String){
        this.answer2 = value
    }
    fun setAns3(value:String){
        this.answer3 = value
    }
    fun setAns4(value:String){
        this.answer4 = value
    }
    fun setSol(value:Int){
        this.solution = value
    }



}