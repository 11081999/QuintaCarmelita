package QuintaCarmelita.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String[] Nombres = new String[]{
        "Maria","Mario","Diana","Diego","Carla","Carlos","Mariana","Janeth","Jose"};

    //Real time database variables
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    private String inputKidNmae;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AutoCompleteTextView edit =findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Nombres);
        edit.setAdapter(adapter);
        ///READING -----!
        //Instance Database
        mFirebaseDatabaseInstance= FirebaseDatabase.getInstance();
        // get reference to 'niños' node
        mFireBaseDatabase = mFirebaseDatabaseInstance.getReference("Niños");

        mFireBaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showFireBaseKidData(dataSnapshot);
                Kid kidInfo = new Kid();
                kidInfo.setName(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getName());

                kidInfo.setName(dataSnapshot.child("rob").getValue(Kid.class).getName());

                kidInfo.setAge(dataSnapshot.child("rob").getValue(Kid.class).getAge());
                System.out.println(kidInfo.getName());
                System.out.println(kidInfo.getAge());

                String ageToString= Integer.toString(kidInfo.getAge());
                updateProfileTextViewes(kidInfo.getName(),ageToString);


                //fillArray(dataSnapshot);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void fillArray(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            /*
                Kid kidInfo = new Kid();
                iteracion por todos los niños
                llenas el arrray nombre con sus nobers

                for(int i= 0; 0<array lenght; i++)
                kidInfo.setName(ds.child( i ).getValue(Kid.class).getName());

                ->kidInfo.setName(ds.getValue(Kid.class).getName());

                nombres.add(kidInfo.getName())
                */
        }

    }

    public void updateProfileTextViewes(String nameOfKid, String ageOfKid) {
        TextView profileKidName = (TextView)findViewById(R.id.textViewName);
        profileKidName.setText(nameOfKid);

        TextView profileKidAge = (TextView)findViewById(R.id.textViewAge);
        profileKidAge.setText(ageOfKid);
    }

    public void updateKidProfileOnFirebaseDatabase() {
        //UPDATE
        //mFirebaseDatabaseInstance.getReference("Niños").child("rob").child("name").setValue("rob2");
        //or
        //mFireBaseDatabase.child("rob").child("name").setValue("rob2");
    }

    public void eliminateKidProfileOnFirebaseDatabase() {
        //Delete
        //mFirebaseDatabaseInstance.getReference("Niños").child("rob").removeValue();
        //or
        //mFireBaseDatabase.child("rob").removeValue();
    }

    public void showFireBaseKidData(DataSnapshot dataSnapshot){
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            //kidInfo.setName(ds.child("rob").getValue(Kid.class).getName());
            //kidInfo.setAge(ds.child("rob").getValue(Kid.class).getAge());

            //profileDisplayInformation.add(kidInfo.getName());
            //profileDisplayInformation.add(kidInfo.getAge());




            //TextView profileKidAge = (TextView)findViewById(R.id.textViewAge);
            //profileKidAge.setText(kidInfo.getAge());
        }
    }
}