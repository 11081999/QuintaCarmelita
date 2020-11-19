
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

public class ProfilePerfilMedico  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        EditText name=(EditText) findViewById(R.id.MedNom);
        EditText sur=(EditText) findViewById(R.id.MedSur);
        String string1=name.toString();
        String string2=sur.toString();
        String full=string1+" "+string2; //Nombre y apellido juntos
        setContentView(R.layout.activity_profile_perfilmedico);
        AutoCompleteTextView tv=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView5);
        tv.setText(getIntent().getStringExtra("NOM"));
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
