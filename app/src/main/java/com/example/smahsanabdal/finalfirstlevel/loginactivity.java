package com.example.smahsanabdal.finalfirstlevel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class loginactivity extends AppCompatActivity {

    private FirebaseDatabase myDatabase;
    private DatabaseReference myReference;
    private FirebaseAuth mFireBaseAuth;
    private String userId;
    private static final int RC_SIGN_IN = 123;
    private ProgressDialog progressDialog;
    Context context;
    User_Data data;
    List<String> dept;

    private List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()
    );


    public void activityForget(View v) {
        Intent i = new Intent(this, forgotpassword.class);
        startActivity(i);
    }

    public void activitySignup(View v) {
        Intent i = new Intent(this, signup.class);
        startActivity(i);
    }

    public void mainscreen(View v) {
        Intent i = new Intent(this, mainscreen.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFireBaseAuth = FirebaseAuth.getInstance();

        myDatabase = FirebaseDatabase.getInstance();
        myReference = myDatabase.getReference().child("abb");
        context=this;
        progressDialog = new ProgressDialog(this);
        data=new User_Data();
        Button signin = findViewById(R.id.email_sign_in_button);
        signin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFireBaseAuth.getCurrentUser() == null)
                    userId = mFireBaseAuth.getCurrentUser().getUid();


                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN);
            }
        });


    }

    public void checkIfUserExist() {

        userId = mFireBaseAuth.getCurrentUser().getUid();
        data.setuid(userId);
        myReference.push().child("Users").push().setValue(data);
        AlertDialog adt=new AlertDialog.Builder(context).setTitle("Choose Dept").setView(getLayoutInflater().inflate(R.layout.list_dia,null)).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                View v=getLayoutInflater().inflate(R.layout.list_dia,null);
                ListView lv=v.findViewById(R.id.list);
                dept=new ArrayList<String>();
                dept.add("Dept2");
                dept.add("Dept3");
                dept.add("Dept4");
                dept.add("Dept5");
                ArrayAdapter<String> adt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,dept);
                lv.setAdapter(adt);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        data.setType(dept.get(position).toString());

                    }
                });
            }
        }).create();

        adt.show();
        /*myReference.child("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("type").getValue().equals("owner")) {

                        comingBackOwner();

                    } else if (dataSnapshot.child("type").getValue().equals("Deliveryboy")){

                        comingBackDeliveryBoy();

                    } else{

                        comingBackSalesman();

                    }
                } else {

                    SignUpUI();

                }*/
        progressDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {

            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {

                //buttonActive=false;
                progressDialog.setMessage("Loading");
                progressDialog.setCancelable(false);
                progressDialog.show();
                checkIfUserExist();

            } else {

                if (response == null) {
                    // User pressed back button
                    Toast.makeText(getApplicationContext(), "SignIn cancelled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        }
    }
}