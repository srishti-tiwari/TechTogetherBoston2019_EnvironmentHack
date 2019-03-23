package techtogether.io.myapplication;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements IClickEvents{

    GoogleSignInClient googleSignInClient;
    DatabaseReference mRootRef;
    public ArrayList<ToDoTask> toDoTaskList = new ArrayList<ToDoTask>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        TextView textViewwelcome=findViewById(R.id.textViewwelcome);
        TextView textViewuser=findViewById(R.id.textViewuser);
        Button buttonprofile=findViewById(R.id.buttonprofile);
        ImageView imageViewuser=findViewById(R.id.imageViewuser);

        //dummy data
        toDoTaskList.add(new ToDoTask("A"));
        toDoTaskList.add(new ToDoTask("B"));
        toDoTaskList.add(new ToDoTask("C"));
        toDoTaskList.add(new ToDoTask("D"));
        toDoTaskList.add(new ToDoTask("E"));
        toDoTaskList.add(new ToDoTask("F"));
        toDoTaskList.add(new ToDoTask("G"));
        toDoTaskList.add(new ToDoTask("H"));

        // add dependency in build.gradle app    implementation 'com.android.support:recyclerview-v7:28.0.0'
        mRecyclerView = findViewById(R.id.my_recycler_view);
        getToDoTasks();

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                deleteTodoTask(position);
            }
            public void onLeftClicked(int position) {
                Toast toast = Toast.makeText(getApplicationContext(), "This is a message displayed in a Toast", Toast.LENGTH_SHORT); toast.show();
                toast.show();
            }
        });


        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


        mRootRef = FirebaseDatabase.getInstance().getReference();
        setTitle("Profile");
        String imageUrl=getIntent().getExtras().getString(MainActivity.IMAGEURL);
        String userName=getIntent().getExtras().getString(MainActivity.USERNAME);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        textViewuser.setText(userName);
        buttonprofile.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                 Intent i=new Intent(HomeActivity.this,PersonalDetailActivity.class);
                                                 startActivity(i);
                                             }
                                         });
       if(imageUrl.equals("none")){
            imageViewuser.setImageResource(R.drawable.common_google_signin_btn_icon_light);
        }
        else
            Glide.with(this).load(imageUrl).into(imageViewuser);
        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,PersonalDetailActivity.class);
                startActivity(i);
            }
        });

}


    // this will be called while loading the list first time and in delete we have added the same to refresh
    private void getToDoTasks() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(toDoTaskList,this);  // add your list
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void deleteTodoTask(int position) {
        toDoTaskList.remove(position);
        getToDoTasks();
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        ArrayList<ToDoTask> TodoTasksList;
        IClickEvents iClickEvents;

        public MyAdapter(ArrayList<ToDoTask> TodoTasksList, IClickEvents iClickEvents) {
            this.TodoTasksList = TodoTasksList;
            this.iClickEvents = iClickEvents;
        }


        // add your custom layout file
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_todotask_layout, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            ToDoTask toDoTask = TodoTasksList.get(position);

            holder.toDoTaskName.setText(toDoTask.taskName);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    iClickEvents.deleteTodoTask(position);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return TodoTasksList.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            // each data item is just a string in this case
            public TextView toDoTaskName;

            public MyViewHolder(View v) {
                super(v);
                this.toDoTaskName = v.findViewById(R.id.textViewToDoTaskName);
            }

        }

    }

}
