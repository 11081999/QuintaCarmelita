package quintacarmelita.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignedInActivity extends AppCompatActivity {
    String[] free=new String[]{

    };
    Button btn_bs;
    ArrayList<String> Nombre=new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    private DBFirebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        db = new DBFirebase();
        db.setCurrentUser();

        final ArrayList<String> red=new ArrayList<>();
        final ArrayAdapter list=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,red);
        final AutoCompleteTextView text=findViewById(R.id.autoCompleteTextView3);
        text.setAdapter(list);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Niños");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                red.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    red.add(snapshot.getKey());

                }
                list.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Button btn_bs =findViewById(R.id.button);
        btn_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignedInActivity.this, ProfileMenuActivity.class);
                intent.putExtra("NOM",text.getText().toString());
                startActivity(intent);

            //btn_bs =findViewById(R.id.button);
            //btn_bs.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
                /*
                Intent intent=new Intent(SignedInActivity.this, ProfileActivity.class);
                intent.putExtra("Valor",text.getText().toString());
                startActivity(intent);
                 */

            }
        });
    }

    public void onLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void createKid(View view) {
        Intent intent=new Intent(SignedInActivity.this, ProfileCreateActivity.class);
        startActivity(intent);
    }

    public void createAccount(View view) {
        Intent intent=new Intent(SignedInActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    public void goToCalendar(View view){
        Intent intent=new Intent(SignedInActivity.this, ProfileCalendario.class);
        startActivity(intent);
    }


    public void getUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to authenticate with your backend server, if you  have one. Use FirebaseUser.getToken() instead
            String uid = user.getUid();
        }

    }
}