package quintacarmelita.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileCreateActivity extends AppCompatActivity {



    //Real time database variabless
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    //values to store
    private EditText inputKidName, inputKidAge;

    private String kidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_create);

        final ArrayList<String> red=new ArrayList<>();
        final ArrayAdapter list=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,red);
        final AutoCompleteTextView text=findViewById(R.id.autoCompleteTextView);
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

        inputKidName    = (EditText) findViewById(R.id.kidName);
        inputKidAge     = (EditText) findViewById(R.id.kidAge);

        //Instance Database
        mFirebaseDatabaseInstance= FirebaseDatabase.getInstance();
        Button btn_bs =findViewById(R.id.button2);
        btn_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Antes era ProfileActivity pero esa ya no existe
                Intent intent=new Intent(ProfileCreateActivity.this, ProfileCreateActivity.class);
                startActivity(intent);

            }
        });
    }

    public void onSaveCreatedClicked(View view) {

        String nameInput    = inputKidName.getText().toString().trim();

        String heightString = inputKidAge.getText().toString();
        final int ageInput = Integer.parseInt(heightString);

        //Referance to the user
        mFireBaseDatabase= mFirebaseDatabaseInstance.getReference("Niños");

        Kid currentKid= new Kid(nameInput.toString(), ageInput, 0.1, "", "", "",
                "", "", "", "", "", "",
                "", "", "", "",
                "", "", "", "");;
        kidId= currentKid.getName();

        mFireBaseDatabase.child(kidId).setValue(currentKid);

        Intent intent=new Intent(ProfileCreateActivity.this, SignedInActivity.class);
        startActivity(intent);

    }
}