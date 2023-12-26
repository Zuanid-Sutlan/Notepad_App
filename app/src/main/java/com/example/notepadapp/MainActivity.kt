package com.example.notepadapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


//    private lateinit var db: MyDataBase
    private var data = ArrayList<Notes>()
    private lateinit var viewModel: ViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data delete in room database
        val delTitle: String? = intent.getStringExtra("DelTitle")
        val delDesc = intent.getStringExtra("DelDescription")
        if (delTitle != null) {
            if (delDesc != null) {
                if (delTitle.isNotBlank() && delDesc.isNotBlank()) {
                    val delNote = Notes(delTitle, delDesc)
                    try {
                        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
                        viewModel.delete(delNote)
                    } catch (e: Exception){
                        Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                        Log.d("delete_error", e.toString())
                    }
                } else {
                    Toast.makeText(this, "dell data is null", Toast.LENGTH_SHORT).show()
                }
            }
        }


        //         Initialize and open the database using runCatching
        lifecycleScope.launch {
            try {

                //                db = MyDataBase.getDataBase(applicationContext)
                viewModel = ViewModelProvider(this@MainActivity).get(ViewModel::class.java)


                //                Retrieve data from the database
                retriveData()
                //                data.addAll(viewModel.getAll() as ArrayList<Notes>)


                // Set up RecyclerView
                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = Adapter(this@MainActivity, data)


            } catch (e: Exception) {

                // Handle any exceptions that occurred during database setup
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Error $e", Toast.LENGTH_LONG).show()
                Log.d("Database_Error", e.toString())
            }
        }


//

        // Set up FloatingActionButton
        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            Log.d("new note click error", "new note click error")
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }

    private fun retriveData() {
        viewModel.getAll().observe(this) { newData ->
            data.clear()
            data.addAll(newData)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    /**
     * override fun onDestroy() {
    // Close the database when the activity is destroyed
    if (::db.isInitialized && db.isOpen) {
    db.close()
    }
    super.onDestroy()
    }
     */
}


//    private val db by lazy { Room.databaseBuilder(
//        applicationContext, MyDataBase::class.java, "MyDataBase"
//    ).build()  }

//    private var data = ArrayList<notes>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val db = Room.databaseBuilder(
//            applicationContext, MyDataBase::class.java, "MyDataBase"
//        ).build()
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
//
//        data = db.noteDao().getAll()
//
//
//
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = Adapter(this, data)
//
//
//        btnAdd.setOnClickListener {
//            startActivity(Intent(this, AddNoteActivity::class.java))
//        }
//
//    }
//}