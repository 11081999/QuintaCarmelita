package QuintaCarmelita.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String[] Nombres = new String[]{
        "Maria","Mario","Diana","Diego","Carla","Carlos","Mariana","Janeth","Jose"};
    ArrayList<String> Nombre=new ArrayList<>();

    //Real time database variables
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    private String inputKidNmae;

    private ValueEventListener mListener;

    Button buttonDelete;
    Button buttonUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        buttonDelete= findViewById(R.id.button5);
        buttonUpdate= findViewById(R.id.button3);



        final ArrayList<String> red=new ArrayList<>();
        final ArrayAdapter list=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,red);
        final AutoCompleteTextView text=findViewById(R.id.autoCompleteTextView2);
        text.setAdapter(list);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Niños");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                red.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    red.add(snapshot.getKey().toString());
                }
                list.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ///READING -----!
        //Instance Database
        Button btn=(Button) findViewById(R.id.button2);
        mFirebaseDatabaseInstance= FirebaseDatabase.getInstance();
        // get reference to 'niños' node
        mFireBaseDatabase = mFirebaseDatabaseInstance.getReference("Niños");
        mListener= new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showFireBaseKidData(dataSnapshot);
                if (getIntent().getStringExtra("Valor")!= null){
                    text.setText(getIntent().getStringExtra("Valor"));
                }



                Kid kidInfo = new Kid();
                //kidInfo.setName(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getName());

                kidInfo.setName(dataSnapshot.child(text.getText().toString()).getValue(Kid.class).getName());

                kidInfo.setAge(dataSnapshot.child(text.getText().toString()).getValue(Kid.class).getAge());
                System.out.println(kidInfo.getName());
                System.out.println(kidInfo.getAge());

                inputKidNmae= text.getText().toString();

                String ageToString= Integer.toString(kidInfo.getAge());
                updateProfileTextViewes(kidInfo.getName(),ageToString);

                //Yolo

                //fillArray(dataSnapshot);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mFireBaseDatabase.addValueEventListener(mListener);

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                eliminateKidProfileOnFirebaseDatabase(inputKidNmae);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                updateKidProfileOnFirebaseDatabase(inputKidNmae);
            }
        });


    }

    public void updateProfileTextViewes(String nameOfKid, String ageOfKid) {
        TextView profileKidName = (TextView)findViewById(R.id.textViewName);
        profileKidName.setText(nameOfKid);

        TextView profileKidAge = (TextView)findViewById(R.id.textViewAge);
        profileKidAge.setText(ageOfKid);
    }


    public void updateKidProfileOnFirebaseDatabase(String key) {
        DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key).child("name");
        drKid.setValue("rob2");
    }

    public void eliminateKidProfileOnFirebaseDatabase(String key) {
        //mFireBaseDatabase.removeEventListener(mListener);
        DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);
        drKid.removeValue();
        Intent intent=new Intent(ProfileActivity.this, SignedInActivity.class);
        startActivity(intent);
    }

}