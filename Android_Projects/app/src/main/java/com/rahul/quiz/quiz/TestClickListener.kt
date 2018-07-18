package com.rahul.quiz.quiz

import android.view.View


interface TestClickListener {
    fun onclick(view: View, position: Int, islongclick: Boolean)
}
