package com.rahul.quiz.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.squareup.picasso.Picasso

import org.w3c.dom.Text

class StartTest : AppCompatActivity(), View.OnClickListener {
    private var option1_but: Button? = null
    private var option2_but: Button? = null
    private var option3_but: Button? = null
    private var option4_but: Button? = null
    private var next_but: Button? = null
    private var question_txt_view: TextView? = null
    private var srore_txt_view: TextView? = null
    private var tot_ques_view: TextView? = null
    private var progressBar: ProgressBar? = null
    internal var tot_Question_numeric: Int = 0
    internal var start = 0
    internal var prograss_value = 0
    internal var my_Question: Int = 0
    internal var timming = 60
    internal lateinit var timer: CountDownTimer
    internal var tot_score: Int = 0
    internal var correctopt: Int = 0
    internal lateinit var checkbutton: String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_test)
        option1_but = findViewById(R.id.option1) as Button
        option2_but = findViewById(R.id.option2) as Button
        option3_but = findViewById(R.id.option3) as Button
        option4_but = findViewById(R.id.option4) as Button
        next_but = findViewById(R.id.next_but) as Button
        question_txt_view = findViewById(R.id.question_text) as TextView
        srore_txt_view = findViewById(R.id.srore) as TextView
        tot_ques_view = findViewById(R.id.tot_question) as TextView
        progressBar = findViewById(R.id.prograsbar) as ProgressBar

        option1_but!!.setOnClickListener(this)
        option2_but!!.setOnClickListener(this)
        option3_but!!.setOnClickListener(this)
        option4_but!!.setOnClickListener(this)
        next_but!!.setOnClickListener(this)

    }

    private fun DefaultButtonColor() {
        option1_but!!.setBackgroundColor(Color.WHITE)
        option1_but!!.setTextColor(Color.parseColor("#FFFF4081"))

        option2_but!!.setBackgroundColor(Color.WHITE)
        option2_but!!.setTextColor(Color.parseColor("#FFFF4081"))

        option3_but!!.setBackgroundColor(Color.WHITE)
        option3_but!!.setTextColor(Color.parseColor("#FFFF4081"))

        option4_but!!.setBackgroundColor(Color.WHITE)
        option4_but!!.setTextColor(Color.parseColor("#FFFF4081"))


    }

    override fun onClick(v: View) {
        if (v === next_but) {

            timer.cancel()
            if (start < tot_Question_numeric) {
                if (checkbutton == GlobalData.listquestion[start].correctop) {
                    correctopt++
                    tot_score += 10
                    change_Question(++start)
                } else {
                    change_Question(++start)
                }
            }
        }
        if (v === option1_but) {
            option1_but!!.setBackgroundColor(Color.parseColor("#FFFF4081"))
            option1_but!!.setTextColor(Color.WHITE)

            option2_but!!.setBackgroundColor(Color.WHITE)
            option2_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option3_but!!.setBackgroundColor(Color.WHITE)
            option3_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option4_but!!.setBackgroundColor(Color.WHITE)
            option4_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            checkbutton = "1"
        }
        if (v === option2_but) {
            option2_but!!.setBackgroundColor(Color.parseColor("#FFFF4081"))
            option2_but!!.setTextColor(Color.WHITE)

            option1_but!!.setBackgroundColor(Color.WHITE)
            option1_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option3_but!!.setBackgroundColor(Color.WHITE)
            option3_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option4_but!!.setBackgroundColor(Color.WHITE)
            option4_but!!.setTextColor(Color.parseColor("#FFFF4081"))
            checkbutton = "2"
        }
        if (v === option3_but) {
            option3_but!!.setBackgroundColor(Color.parseColor("#FFFF4081"))
            option3_but!!.setTextColor(Color.WHITE)

            option1_but!!.setBackgroundColor(Color.WHITE)
            option1_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option2_but!!.setBackgroundColor(Color.WHITE)
            option2_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option4_but!!.setBackgroundColor(Color.WHITE)
            option4_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            checkbutton = "3"
        }
        if (v === option4_but) {
            option4_but!!.setBackgroundColor(Color.parseColor("#FFFF4081"))
            option4_but!!.setTextColor(Color.WHITE)

            option2_but!!.setBackgroundColor(Color.WHITE)
            option2_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option3_but!!.setBackgroundColor(Color.WHITE)
            option3_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            option1_but!!.setBackgroundColor(Color.WHITE)
            option1_but!!.setTextColor(Color.parseColor("#FFFF4081"))

            checkbutton = "4"
        }

    }

    override fun onResume() {
        super.onResume()
        tot_Question_numeric = GlobalData.listquestion.size
        timer = object : CountDownTimer(time, delay) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar!!.progress = prograss_value
                prograss_value++
                timming = 60
                timming = timming - prograss_value
                srore_txt_view!!.setText(String.format("00:%d", timming))
            }

            override fun onFinish() {
                timer.cancel()
                change_Question(++start)
            }
        }
        change_Question(start)
    }

    override fun onPause() {
        super.onPause()
        timer.onFinish()
    }

    private fun change_Question(start: Int) {
        DefaultButtonColor()
        if (start < tot_Question_numeric) {
            my_Question++
            tot_ques_view!!.setText(String.format("%d/%d", my_Question, tot_Question_numeric))
            progressBar!!.progress = 0
            prograss_value = 0
            question_txt_view!!.text = GlobalData.listquestion[start].question
            option1_but!!.text = GlobalData.listquestion[start].option1
            option2_but!!.text = GlobalData.listquestion[start].option2
            option3_but!!.text = GlobalData.listquestion[start].option3
            option4_but!!.text = GlobalData.listquestion[start].option4
            timer.start()
        } else {
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show()
            val intent = Intent(this, result::class.java)
            val data = Bundle()
            data.putInt("score", tot_score)
            data.putInt("tot", tot_Question_numeric)
            data.putInt("correctop", correctopt)
            intent.putExtras(data)
            startActivity(intent)
            finish()

        }
    }

    companion object {
        internal val delay: Long = 2000
        internal val time: Long = 60000
    }

}
