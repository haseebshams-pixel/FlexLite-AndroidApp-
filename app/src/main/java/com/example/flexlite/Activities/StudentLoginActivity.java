package com.example.flexlite.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.flexlite.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email;
    TextInputEditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        mAuth = FirebaseAuth.getInstance();
        ImageView backButton = (ImageView) this.findViewById(R.id.back1);
        email = (EditText) findViewById(R.id.studUsername);
        password = (TextInputEditText) findViewById(R.id.password);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLoginActivity.this, StartActivity.class));

            }
        });
        View signIn = (View) this.findViewById(R.id.signin1);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkAllFields()){
                    signin(email.getText().toString(), password.getText().toString());
                }
            }
        });

    }

    public void updateUI(FirebaseUser currentUser) {
        startActivity(new Intent(StudentLoginActivity.this, StudentHomeActivity.class));
    }

    public boolean checkAllFields(){
        if(email.getText().toString().isEmpty()){
            email.setError("Email Required");
            return false;
        }
        if(password.getText().toString().isEmpty()){
            password.setError("Password Required");
            return false;
        }
        return true;
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            updateUI(currentUser);
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(StudentLoginActivity.this, StartActivity.class));
    }

    void signin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(StudentLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}