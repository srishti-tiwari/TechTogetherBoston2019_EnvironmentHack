package techtogether.io.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalDetailActivity extends AppCompatActivity {

    DatabaseReference mRootRef;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);

        Button submitButton = findViewById(R.id.submitButton);
        final Button signoutButton = findViewById(R.id.signoutButton);
        final EditText name = findViewById(R.id.nameValTextView);
        final EditText location = findViewById(R.id.locationValTextView2);
        final EditText gender = findViewById(R.id.genderValTextView);
        final EditText birthday = findViewById(R.id.birthdayValTextView);
       // String email = getIntent().getExtras().getString(MainActivity.EMAIL);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get values from textboxes + email
                //do some inserts to firebase

                mRootRef = FirebaseDatabase.getInstance().getReference();

                String id = mRootRef.push().getKey();
                User user = new User(name.getText().toString(), location.getText().toString(), gender.getText().toString(), birthday.getText().toString());
                user.setName(name.getText().toString());
                user.setLocation(location.getText().toString());
                user.setGender(gender.getText().toString());
                user.setBirthday(birthday.getText().toString());
                mRootRef.child("users").child(id).setValue(user);
                Toast.makeText(getApplicationContext(), "Added to the DB!", Toast.LENGTH_SHORT).show();
            }
        });
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();


                googleSignInClient.signOut()
                        .addOnCompleteListener(PersonalDetailActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(PersonalDetailActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });


        //findViewById( )

    }

}
