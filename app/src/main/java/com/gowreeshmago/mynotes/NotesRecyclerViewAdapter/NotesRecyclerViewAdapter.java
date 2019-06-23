package com.gowreeshmago.mynotes.NotesRecyclerViewAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gowreeshmago.mynotes.NotesObject.NotesObject;
import com.gowreeshmago.mynotes.R;

import java.util.ArrayList;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.Viewholder>{



    ArrayList<NotesObject> notesObjectArrayList = new ArrayList<>();

    //step e is to declare onnote here

    OnNoteClickListenerInterface onNoteClickListenerInterfaceSuperClass;


    public NotesRecyclerViewAdapter(ArrayList<NotesObject> notesObjectArrayList, OnNoteClickListenerInterface onNoteClickListenerInterfaceSuperClass) {
        this.notesObjectArrayList = notesObjectArrayList;
        this.onNoteClickListenerInterfaceSuperClass = onNoteClickListenerInterfaceSuperClass;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_list_items,viewGroup,false);

        Viewholder viewholder = new Viewholder(view,onNoteClickListenerInterfaceSuperClass);


        return viewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {

        viewholder.title.setText(notesObjectArrayList.get(i).getTitle());
        viewholder.timeStamp.setText(notesObjectArrayList.get(i).getTimestamp());


    }

    @Override
    public int getItemCount() {
        return notesObjectArrayList.size();
    }







    public class Viewholder extends RecyclerView.ViewHolder {

        TextView title,timeStamp;

        //step d is to use the onnote interface here

        OnNoteClickListenerInterface onNoteClickListenerInterface;


        public Viewholder(@NonNull View itemView, final OnNoteClickListenerInterface onNoteClickListenerInterface) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            timeStamp=itemView.findViewById(R.id.timestamp);

            this.onNoteClickListenerInterface = onNoteClickListenerInterface;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNoteClickListenerInterface.onNoteClickMethod(getAdapterPosition());
                }
            });

        }


    }


    //step a for onclick listener create an interface
    //step b is to implements this method in the mainActivity

    //we still need to use the onclicklistener because this is not enough. use the onclicklistener provided by the android
    //int the viewholder class

    //step c implement the onclick listener in the viewholder class

    public interface OnNoteClickListenerInterface{
        void onNoteClickMethod(int position);
    }




}
