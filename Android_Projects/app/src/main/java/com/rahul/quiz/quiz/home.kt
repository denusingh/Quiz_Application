package com.rahul.quiz.quiz

import android.content.Intent
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


import org.w3c.dom.Text

import java.util.ArrayList

class home : AppCompatActivity() {
    internal lateinit var clist: MutableList<Test>
    private val user_text: TextView? = null
    private val cur_user: String? = null
    private val sign_out_but: Button? = null
    private var mAuth: FirebaseAuth? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val menu: Menu? = null
    private val start_but: Button? = null
    private var list: RecyclerView? = null
    private var mFirebaseRef: DatabaseReference? = null
    internal lateinit var database: FirebaseDatabase

    private val inBed = false
    internal var personname: String? = null
    internal var personemail: String? = null
    internal lateinit var TopNavigationView: BottomNavigationView

    internal fun allUserinterFace() {

        list = findViewById(R.id.test_list) as RecyclerView
        database = FirebaseDatabase.getInstance()
        mFirebaseRef = database.getReference("Test")
        mFirebaseRef!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                clist = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {

                    val id = dataSnapshot1.ref.key
                    val value = dataSnapshot1.getValue(Test::class.java)
                    val fire = Test()
                    val name = value!!.tesT_NAME
                    val image = value.tesT_IMAGE
                    fire.tesT_NAME = name
                    fire.tesT_ID = id
                    fire.tesT_IMAGE = image
                    clist.add(fire)

                }

                val recycler = RecyclerviewAdapter(clist)
                val layoutmanager = LinearLayoutManager(applicationContext)
                list!!.setHasFixedSize(true)
                list!!.layoutManager = layoutmanager
                list!!.adapter = recycler
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        TopNavigationView = findViewById(R.id.top_navigation) as BottomNavigationView
        val acct = GoogleSignIn.getLastSignedInAccount(applicationContext)
        if (acct != null) {
            personname = acct.email
            personemail = acct.givenName

            GlobalData.user = personname
            GlobalData.email = personemail
            val menuItem = TopNavigationView.menu.getItem(1).setTitle(personemail)


        }

        TopNavigationView.setOnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.sign_out -> signOut()
            }


            true
        }

        //show all test with recycleview and  testlayout
        allUserinterFace()
    }

    fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()
        // Google sign out
        mGoogleSignInClient!!.signOut().addOnCompleteListener(this
        ) {
            val i = Intent(this@home, WelcomeActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
