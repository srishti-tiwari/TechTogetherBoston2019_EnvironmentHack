package techtogether.io.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_account );

        Button submitButton = findViewById( R.id.submitButton );
        Button cancelButton = findViewById( R.id.cancelButton );

        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get values from textboxes + email
                TextView name = findViewById( R.id.nameValTextView );
                TextView location = findViewById( R.id.locationValTextView );
                TextView gender = findViewById( R.id.genderValTextView );
                TextView birthday = findViewById( R.id.birthdayValTextView );
                String email = getIntent().getExtras().getString( MainActivity.EMAIL );

                //do some inserts to firebase


            }
        } );

        cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }
}
