package com.rahul.quiz.quiz

import android.content.Intent
import android.support.annotation.IntegerRes
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import org.w3c.dom.Text

class result : AppCompatActivity(), View.OnClickListener {
    private var email_text: TextView? = null
    private var username_text: TextView? = null
    private var score_text: TextView? = null
    private var passed_text: TextView? = null
    private var try_but: Button? = null
    private var db: FirebaseDatabase? = null
    private var mref: DatabaseReference? = null
    private var personname: String? = null
    private var personemail: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        email_text = findViewById(R.id.email_value) as TextView
        username_text = findViewById(R.id.username_value) as TextView
        score_text = findViewById(R.id.score_value) as TextView
        passed_text = findViewById(R.id.corect_answer_value) as TextView
        try_but = findViewById(R.id.try_but) as Button
        try_but!!.setOnClickListener(this)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            personemail = acct.email
            personname = acct.givenName
        }

        db = FirebaseDatabase.getInstance()
        mref = db!!.getReference("Test_Score")
        val extra = intent.extras
        if (extra != null) {
            val score = extra.getInt("score")
            val total = extra.getInt("tot")
            val correctoption = extra.getInt("correctop")
            score_text!!.text = score.toString()
            username_text!!.text = personname
            email_text!!.text = personemail
            passed_text!!.text = correctoption.toString() + "/" + total.toString()
            mref!!.child(personname + "_testid_" + GlobalData.test_id.toString())
                    .setValue(TestScore(
                            this!!.personname!!,
                            score.toString()))

        }

    }

    override fun onClick(v: View) {
        if (v === try_but) {
            val i = Intent(this, home::class.java)
            startActivity(i)
            finish()
        }
    }
}
