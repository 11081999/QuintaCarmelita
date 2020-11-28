package quintacarmelita.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileMenuActivity  extends AppCompatActivity {
    public String blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        setContentView(R.layout.activity_profile_menu);
        final ArrayList<String> red=new ArrayList<>();
        final ArrayAdapter list=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,red);
        final AutoCompleteTextView text=findViewById(R.id.autoCompleteTextView4);
        text.setAdapter(list);
        text.setText(getIntent().getStringExtra("NOM"));
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
        Button bot=(Button) findViewById(R.id.butttonDatosGenerales);
        final String fer;
        fer=text.getText().toString();
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileMenuActivity.this, ProfileDatosGenerales.class);
                intent.putExtra("NOM",text.getText().toString());
                startActivity(intent);
            }
        });
        Button bot1=(Button) findViewById(R.id.buttonPerfilMedico);
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileMenuActivity.this, ProfilePerfilMedico.class);
                intent.putExtra("NOM",text.getText().toString());
                startActivity(intent);
            }
        });
        Button bot2=(Button) findViewById(R.id.buttonBitacora);
        bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileMenuActivity.this, ProfileBitacora.class);
                intent.putExtra("NOM",text.getText().toString());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        }

    public void clickedButtonDatosGenerales(View view) {
        //Intent intent=new Intent(ProfileMenuActivity.this, ProfileDatosGenerales.class);
        //intent.putExtra("NOM",blue);
        //startActivity(intent);
    }

    public void clickedbuttonPerfilMedico(View view) {
        //Intent intent=new Intent(ProfileMenuActivity.this, ProfilePerfilMedico.class);
        //startActivity(intent);
    }

    public void clickedbuttonCalendario(View view) {
        //Intent intent=new Intent(ProfileMenuActivity.this, ProfileCalendario.class);
        //startActivity(intent);
    }

    public void clickedbuttonBitacora(View view) {
        //Intent intent=new Intent(ProfileMenuActivity.this, ProfileBitacora.class);
        //startActivity(intent);
    }
}


