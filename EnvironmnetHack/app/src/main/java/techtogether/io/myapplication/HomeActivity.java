package techtogether.io.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        TextView textViewwelcome=findViewById(R.id.textViewwelcome);
        TextView textViewuser=findViewById(R.id.textViewuser);

       String name= (String) getIntent().getExtras().getSerializable("name");
       textViewuser.setText(name);
    }
}
