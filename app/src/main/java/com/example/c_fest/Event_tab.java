package com.example.c_fest;

import android.R.color;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.time.Month;
import java.util.Calendar;
import java.util.regex.Pattern;

import static android.R.*;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Event_tab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Event_tab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Event_tab extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String TAG = "Event_tab";
    private static final int PICK_IMAGE_REQUEST = 234;

    private TextView mdisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetlistener;

    TextView chooseTime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMint;
    String ampm;


// image pic upload ka code

    private ImageView imageView;
    private Button buttonchoose;
    private Uri filePath;

    private StorageReference storageReference;

    //submit event tab kah
    EditText cllgname,cllgfest,venue;
    TextView Time,Date;
    Button submit;
    String v1,v2,v3,v4,v5;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Event_tab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Event_tab.
     */
    // TODO: Rename and change types and number of parameters
    public static Event_tab newInstance(String param1, String param2) {
        Event_tab fragment = new Event_tab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //time picker code
        View view =inflater.inflate(R.layout.fragment_event_tab, container, false);

        //submit code
        cllgname =(EditText) view.findViewById(R.id.cllg_edit);
        cllgfest = (EditText)view.findViewById(R.id.fest_edit);
        venue = (EditText)view.findViewById(R.id.venue_edit);
        Time=(TextView)view.findViewById(R.id.time_edit);
        Date=(TextView)view.findViewById(R.id.date_pick);
        submit=(Button)view.findViewById(R.id.event_btn);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference("Festname");

        submit=view.findViewById(R.id.event_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fl=0;
                if (TextUtils.isEmpty(cllgname.getText()))
                {
                    cllgname.setError("ENTER COLLEGE NAME");
                    cllgname.requestFocus();
                    fl=1;
                }
                else
                {
                    String a = cllgname.getText().toString();
                    cllgname.setText(a);
                }
                if (TextUtils.isEmpty(cllgfest.getText()))
                {
                    cllgfest.setError("ENTER FEST NAME");
                    cllgfest.requestFocus();
                    fl=1;
                }
                else
                {
                    String a = cllgfest.getText().toString();
                    cllgfest.setText(a);
                }if (TextUtils.isEmpty(venue.getText()))
                {
                    venue.setError("ENTER VENUE");
                    venue.requestFocus();
                    fl=1;
                }
                else
                {
                    String a = venue.getText().toString();
                    venue.setText(a);
                }

                if(fl==0) {
                    uploadfile();
                    v1 = cllgname.getText().toString();
                    v2 = cllgfest.getText().toString();
                    v3 = venue.getText().toString();
                    v4 = Time.getText().toString();
                    v5 = Date.getText().toString();
                    Toast.makeText(getContext(), v1 + v2 + v3+ v4 + v5,Toast.LENGTH_LONG).show();
                    eventdatamodel eventdatamodel = new eventdatamodel(v1, v2, v3,v4,v5);
                    databaseReference.push().setValue(eventdatamodel);
                    Toast.makeText(getContext(), "Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

                }
            }
        });


        chooseTime = (TextView) view. findViewById(R.id.time_edit);
        chooseTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour= calendar.get(Calendar.HOUR_OF_DAY);
                currentMint= calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >=12)
                        {
                            ampm = "PM";
                        }
                        else{
                            ampm="AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d",hourOfDay,minute)+ampm);
                        //this is the second option to display the time
                        // chooseTime.setText(hourOfDay+":"+minute+ampm);
                    }
                },currentHour,currentMint,false);

                timePickerDialog.show();
            }
        });
        //image wala code hai idhar
        storageReference= FirebaseStorage.getInstance().getReference();
        imageView = (ImageView)view.findViewById(R.id.image_poster);
        buttonchoose=(Button)view.findViewById(R.id.buttonchoose);
        buttonchoose.setOnClickListener(this);


        // date picker code

        mdisplayDate = (TextView)view.  findViewById(R.id.date_pick);
        mdisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetlistener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();



            }
        });

        mDateSetlistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG, "onDateSet: mm/dd/yyy:" + month +"/"+ dayOfMonth +"/"+year);
                String date = month + "/"+ dayOfMonth +"/"+year;
                mdisplayDate.setText(date);
            }
        };



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    // image chooser code
    private void showFileChooser(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
    }

    private void uploadfile(){

        if (filePath!=null) {

            v1 = cllgname.getText().toString();
            v2 = cllgfest.getText().toString();
            final ProgressDialog progressDialog= new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            StorageReference riversRef = storageReference.child("images/"+v1+":"+v2+"/Poster.jpg");

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "File Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(),exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage(((int) progress)+ "% Uploaded...");
                }
            })
            ;
        }
        else{
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!= null && data.getData()!=null){
            filePath=data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonchoose) {
            // open file chooser
            showFileChooser();
        }

    }

// image wala code yaha khtm


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
