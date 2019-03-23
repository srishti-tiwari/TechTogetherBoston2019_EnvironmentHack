package techtogether.io.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    DatabaseReference mRootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        TextView textViewwelcome=findViewById(R.id.textViewwelcome);
        TextView textViewuser=findViewById(R.id.textViewuser);
        Button buttonsignout=findViewById(R.id.buttonsignout);
        ImageView imageViewuser=findViewById(R.id.imageViewuser);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        setTitle("Profile");
        String imageUrl=getIntent().getExtras().getString(MainActivity.IMAGEURL);
        String userName=getIntent().getExtras().getString(MainActivity.USERNAME);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        textViewuser.setText(userName);
        buttonsignout.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 signOut();
                                             }
                                         });
       if(imageUrl.equals("none")){
            imageViewuser.setImageResource(R.drawable.common_google_signin_btn_icon_light);
        }
        else
            Glide.with(this).load(imageUrl).into(imageViewuser);
        buttonsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

}
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        googleSignInClient.signOut()
                .addOnCompleteListener(HomeActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
