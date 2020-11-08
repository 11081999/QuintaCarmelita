package QuintaCarmelita.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import androidx.appcompat.app.AppCompatActivity;

public class ProfileCreateActivity extends AppCompatActivity {



    //Real time database variables
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    //values to store
    private EditText inputKidName, inputKidAge;

    private String kidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_create);

        inputKidName    = (EditText) findViewById(R.id.kidName);
        inputKidAge     = (EditText) findViewById(R.id.kidAge);

        //Instance Database
        mFirebaseDatabaseInstance= FirebaseDatabase.getInstance();
    }

    public void onSaveCreatedClicked(View view) {

        String nameInput    = inputKidName.getText().toString().trim();

        String heightString = inputKidAge.getText().toString();
        final int ageInput = Integer.parseInt(heightString);

        //Referance to the user
        mFireBaseDatabase= mFirebaseDatabaseInstance.getReference("Niños");

        Kid currentKid= new Kid(nameInput.toString(), ageInput);
        kidId= currentKid.getName();

        mFireBaseDatabase.child(kidId).setValue(currentKid);

        Intent intent=new Intent(ProfileCreateActivity.this, SignedInActivity.class);
        startActivity(intent);

    }
}