
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

public class ProfileMenuActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        setContentView(R.layout.activity_profile_menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        }

    public void clickedButtonDatosGenerales(View view) {
        Intent intent=new Intent(ProfileMenuActivity.this, ProfileDatosGenerales.class);
        startActivity(intent);
    }

    public void clickedbuttonPerfilMedico(View view) {
        Intent intent=new Intent(ProfileMenuActivity.this, ProfilePerfilMedico.class);
        startActivity(intent);
    }

    public void clickedbuttonCalendario(View view) {
        //Intent intent=new Intent(ProfileMenuActivity.this, ProfileCalendario.class);
        //startActivity(intent);
    }

    public void clickedbuttonBitacora(View view) {
        Intent intent=new Intent(ProfileMenuActivity.this, ProfileBitacora.class);
        startActivity(intent);
    }
}


