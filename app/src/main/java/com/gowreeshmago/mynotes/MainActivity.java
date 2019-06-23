package com.gowreeshmago.mynotes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.gowreeshmago.mynotes.NotesObject.NotesObject;
import com.gowreeshmago.mynotes.NotesRecyclerViewAdapter.NotesRecyclerViewAdapter;
import com.gowreeshmago.mynotes.utils.ViewspacingObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotesRecyclerViewAdapter.OnNoteClickListenerInterface,
        FloatingActionButton.OnClickListener {


    //ui elements
    RecyclerView recyclerView;

    //variables
    ArrayList<NotesObject> notesObjectArrayList = new ArrayList<>();
    NotesRecyclerViewAdapter notesRecyclerViewAdapter;


    //lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.floatingActionButton).setOnClickListener(this);


        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        setTitle("Notes");

        inflateRecyclerViewLayout();
        makeArrayList();




    }




    private void inflateRecyclerViewLayout() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //decoration
        ViewspacingObject viewspacingObject = new ViewspacingObject(10);
        recyclerView.addItemDecoration(viewspacingObject);


        //attach swipe here
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);


        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(notesObjectArrayList,this);//step e this because the mainactivity already implements the interface
        recyclerView.setAdapter(notesRecyclerViewAdapter);





    }




    private void makeArrayList() {

        for (int i = 0; i < 100; i++){

            NotesObject notesObject = new NotesObject();
            notesObject.setTitle("title #"+i);
            notesObject.setTimestamp("June 2019");
            notesObjectArrayList.add(notesObject);

        }

        notesRecyclerViewAdapter.notifyDataSetChanged();


    }

    //step b
    @Override
    public void onNoteClickMethod(int position) {

        Log.d("click", "onNoteClickMethod: clicked");

        //one has to send the data about which note was selected, therefore one can attach the note object with the intent.
        //object to be transferred should implement the parcelable method

        Intent intent = new Intent(this,com.gowreeshmago.mynotes.NoteActivity.class);
        //send the note object with the intent

        intent.putExtra("selected_note",notesObjectArrayList.get(position));


        startActivity(intent);

    }







    // for the floating action button

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,NoteActivity.class);
        startActivity(intent);
    }


    // method to swipe delete a note in main activity
    private ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            deleteNote(notesObjectArrayList.get(viewHolder.getAdapterPosition()));
        }
    };



    //method to delete the view of the note


    private void deleteNote(NotesObject notesObject)
    {
        notesObjectArrayList.remove(notesObject);
        notesRecyclerViewAdapter.notifyDataSetChanged();
    }








}


















