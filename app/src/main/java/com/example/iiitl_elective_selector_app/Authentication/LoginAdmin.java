package com.example.iiitl_elective_selector_app.Authentication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.iiitl_elective_selector_app.AdminPortal.AdminPortal;
import com.example.iiitl_elective_selector_app.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAdmin extends AppCompatActivity {
    TextView txt_signIn;
    EditText email, pass;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        txt_signIn = findViewById(R.id.txt_signin);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_pass);

        progressDialog = new ProgressDialog(LoginAdmin.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        txt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String Email = email.getText().toString();
                String Pass = pass.getText().toString();

                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Pass)) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginAdmin.this, "Enter valid data", Toast.LENGTH_SHORT).show();
                } else if (Pass.length() < 6) {
                    progressDialog.dismiss();
                    pass.setError("Invalid Password");
                    Toast.makeText(LoginAdmin.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                } else if (!Email.equals("admin@gmail.com")) {
                    progressDialog.dismiss();
                    email.setError("Wrong Email");
                    Toast.makeText(LoginAdmin.this, "No account is registered with this email-id.", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                progressDialog.dismiss();
                                Toast.makeText(LoginAdmin.this, "Welcome to administration page.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginAdmin.this, AdminPortal.class));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginAdmin.this, "Error to login10", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        myDialog = new Dialog(this);
    }
}