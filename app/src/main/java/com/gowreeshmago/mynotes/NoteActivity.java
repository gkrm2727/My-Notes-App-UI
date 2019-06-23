package com.gowreeshmago.mynotes;

import android.app.Activity;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gowreeshmago.mynotes.NotesObject.NotesObject;
import com.gowreeshmago.mynotes.utils.Lines_in_Notes_Object;

public class NoteActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener
        , GestureDetector.OnDoubleTapListener ,View.OnClickListener{


    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;



    //ui components
    Lines_in_Notes_Object lines_in_notes_object;
    EditText editTitle;
    TextView viewTitle;
    RelativeLayout check_container,back_arrow_container;
    ImageButton check_button , back_button;


    //state variable
    int presentState;


    //objects for gesture detection
    GestureDetector gestureDetector;


    //variables
    NotesObject receivedNote;
    boolean isANewNote;



    //lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //initialize views for the layout

        lines_in_notes_object = findViewById(R.id.note_text);
        viewTitle = findViewById(R.id.note_text_title);
        editTitle = findViewById(R.id.note_edit_title);

        //initialize views for the toolbar

        check_container = findViewById(R.id.check_container);
        check_button = findViewById(R.id.toolbar_check);
        back_arrow_container = findViewById(R.id.back_arrow_container);
        back_button = findViewById(R.id.toolbar_back_arrow);


        //Activity has two states the view state and the edit state

        //if a new note go to edit mode
        //else display mode

        if (checkNewNote()) {
            //edit mode
            setNewNoteProperties();
            editModeEnabled();


        } else {
            //view mode
            setOldNoteProperties();
            contentInterationDisabled();

        }

        setTouchLiteners();

    }





    // to handle screen rotations


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mode",presentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);


        presentState = savedInstanceState.getInt("mode");
        if(presentState==EDIT_MODE_ENABLED){
            editModeEnabled();
        }
    }




    //methods







    //manipulating content interaction

    private void contentInteractionEnabled(){
        lines_in_notes_object.setKeyListener(new EditText(this).getKeyListener());
        lines_in_notes_object.setFocusable(true);
        lines_in_notes_object.setFocusableInTouchMode(true);
        lines_in_notes_object.setCursorVisible(true);
        lines_in_notes_object.requestFocus();
    }


    private void contentInterationDisabled(){


        lines_in_notes_object.setKeyListener(null);
        lines_in_notes_object.setFocusable(false);
        lines_in_notes_object.setFocusableInTouchMode(false);
        lines_in_notes_object.setCursorVisible(false);
        lines_in_notes_object.clearFocus();


    }











    public boolean checkNewNote() {

        if (getIntent().hasExtra("selected_note")) {
            receivedNote = getIntent().getParcelableExtra("selected_note");
            isANewNote = false;
            presentState = EDIT_MODE_DISABLED;
        } else {
            isANewNote = true;
            presentState = EDIT_MODE_ENABLED;

        }

        return isANewNote;
    }


    public void setNewNoteProperties() {

        viewTitle.setText("New Note");
        editTitle.setText("Note Title");


    }


    public void setOldNoteProperties() {
        viewTitle.setText(receivedNote.getTitle());
        editTitle.setText(receivedNote.getTitle());
        lines_in_notes_object.setText(receivedNote.getContent());
    }


    //method for changing to edit mode on tapping the edit text.on double tap

    private void editModeEnabled(){
        check_button.setVisibility(View.VISIBLE);
        back_button.setVisibility(View.GONE);
        check_container.setVisibility(View.VISIBLE);
        back_arrow_container.setVisibility(View.GONE);


        editTitle.setVisibility(View.VISIBLE);
        viewTitle.setVisibility(View.GONE);

        presentState = EDIT_MODE_ENABLED;

        contentInteractionEnabled();

    }



    //
    private void editModeDisabled(){
        check_button.setVisibility(View.GONE);
        back_button.setVisibility(View.VISIBLE);
        check_container.setVisibility(View.GONE);
        back_arrow_container.setVisibility(View.VISIBLE);



        editTitle.setVisibility(View.GONE);
        viewTitle.setVisibility(View.VISIBLE);

        presentState = EDIT_MODE_DISABLED;

        contentInterationDisabled();


    }



    //method for detecting tap on the edit text
    private void setTouchLiteners() {

        lines_in_notes_object.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, this);


        viewTitle.setOnClickListener(this);
        check_button.setOnClickListener(this);
        back_button.setOnClickListener(this);

    }





















    //gesture methods

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    //double tap methods

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        //enable edit mode
        editModeEnabled();

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

















    //to register the back button for escaping the edit mode

    @Override
    public void onBackPressed() {


        if (presentState == EDIT_MODE_ENABLED)
        {
            onClick(check_button);
        }else{
            super.onBackPressed();

        }




    }


    //onclick method



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.toolbar_check:
            {

                hideKeyBoardOnExitEdit();
                editModeDisabled();



                break;
            }

            case R.id.note_text_title:
            {
                editModeEnabled();

                //change the cursor focus
                editTitle.requestFocus();

                //send the cursor to the end
                editTitle.setSelection(editTitle.length());

                break;
            }


            case R.id.toolbar_back_arrow:
            {

                finish();



                break;
            }

        }

    }

    private void hideKeyBoardOnExitEdit() {

        InputMethodManager im = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view= this.getCurrentFocus();

        if(view==null)
        {
            view = new View(this);

        }
        im.hideSoftInputFromWindow(view.getWindowToken(),0);



    }
}
