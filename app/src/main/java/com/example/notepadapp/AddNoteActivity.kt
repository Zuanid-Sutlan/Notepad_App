package com.example.notepadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

class AddNoteActivity : AppCompatActivity() {

    private lateinit var db : MyDataBase
    private lateinit var viewModel: ViewModel

    private lateinit var etTitle : EditText
    private lateinit var etDescription : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)




            /** try {
                db = Room.databaseBuilder(
                    applicationContext, MyDataBase::class.java, "MyDataBase"
                ).enableMultiInstanceInvalidation().build()

            } catch (e:Throwable) {
                Toast.makeText(this, "error: $e", Toast.LENGTH_LONG).show()
                Log.d("Database_Error", e.toString())
            } */


        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDesc)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.btn_save -> {

                // room database
                val title : String = etTitle.text.toString()
                val desc : String = etDescription.text.toString()

                if (title.isEmpty() ){
                    etTitle.error = "please enter"
                    return true
                }
                if (desc.isEmpty()){
                    etDescription.error = "please enter"
                    return true
                }

                val note = Notes(title,desc)

                lifecycleScope.launch {
                    try {
//                        db = MyDataBase.getDataBase(applicationContext)
                        viewModel = ViewModelProvider(this@AddNoteActivity).get(ViewModel::class.java)

                        // inserting data

                        viewModel.insert(note)

                    } catch (e: Exception) {
                        Toast.makeText(this@AddNoteActivity, "error $e", Toast.LENGTH_SHORT).show()
                        Log.d("Database_Error", e.toString())
                    }
                }

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}