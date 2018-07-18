package com.rahul.quiz.quiz

import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import java.util.Collections

class StartTestinterface : AppCompatActivity() {
    internal lateinit var db: FirebaseDatabase
    internal lateinit var mref: DatabaseReference
    internal lateinit var fetching_btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_testinterface)
        db = FirebaseDatabase.getInstance()
        mref = db.getReference("Questions")
        fetching_btn = findViewById(R.id.StartTest) as Button
        loadQues(GlobalData.test_id)
        Handler().postDelayed({
            val i = Intent(this@StartTestinterface, StartTest::class.java)
            startActivity(i)
        }, 5000)


    }

    private fun loadQues(test_id: String?) {
        db = FirebaseDatabase.getInstance()
        mref = db.getReference("Questions")

        if (GlobalData.listquestion.size > 0)
            GlobalData.listquestion.clear()

        mref.orderByChild("test_id").equalTo(test_id)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        for (pDataSnapshot in dataSnapshot.children) {

                            val ques = pDataSnapshot.getValue(Questions::class.java)
                            GlobalData.listquestion.get(ques)

                        }

                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
        Collections.shuffle(GlobalData.listquestion)
    }


}

private fun <E> List<E>.clear() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

private fun <E> List<E>.get(index: E?) {}
